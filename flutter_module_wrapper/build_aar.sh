#!/bin/bash

# Script to build Flutter AAR and publish to Maven local (~/.m2/repository)
# Usage: ./build_aar.sh [--build-number VERSION] [--build-mode MODE]
# Example: ./build_aar.sh --build-number 1.0.0
#          ./build_aar.sh --build-number 1.0.0 --build-mode debug

set -e

# Default values
BUILD_NUMBER="1.0.0"
BUILD_MODE="all"  # all, debug, profile, release
M2_REPO="$HOME/.m2/repository"

# Parse arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        --build-number)
            BUILD_NUMBER="$2"
            shift 2
            ;;
        --build-mode)
            BUILD_MODE="$2"
            shift 2
            ;;
        -h|--help)
            echo "Usage: $0 [--build-number VERSION] [--build-mode MODE]"
            echo ""
            echo "Options:"
            echo "  --build-number VERSION   Set the build number/version (e.g., 1.0.0)"
            echo "  --build-mode MODE        Build mode: all, debug, profile, release (default: all)"
            echo "  -h, --help               Show this help message"
            echo ""
            echo "Examples:"
            echo "  $0 --build-number 1.0.0"
            echo "  $0 --build-number 1.0.0 --build-mode debug"
            exit 0
            ;;
        *)
            echo "Unknown option: $1"
            exit 1
            ;;
    esac
done

# Validate build-number
if [[ -z "$BUILD_NUMBER" ]]; then
    echo "Error: --build-number is required"
    echo "Usage: $0 --build-number VERSION [--build-mode MODE]"
    exit 1
fi

echo "=================================================="
echo "Building Flutter AAR"
echo "  Version: $BUILD_NUMBER"
echo "  Mode: $BUILD_MODE"
echo "=================================================="

# Build flutter AAR based on mode
BUILD_ARGS="--build-number $BUILD_NUMBER"

case $BUILD_MODE in
    all)
        echo ""
        echo ">>> Building all modes (debug, profile, release)..."
        flutter build aar $BUILD_ARGS
        ;;
    debug)
        echo ""
        echo ">>> Building debug mode..."
        flutter build aar $BUILD_ARGS --no-profile --no-release
        ;;
    profile)
        echo ""
        echo ">>> Building profile mode..."
        flutter build aar $BUILD_ARGS --no-debug --no-release
        ;;
    release)
        echo ""
        echo ">>> Building release mode..."
        flutter build aar $BUILD_ARGS --no-debug --no-profile
        ;;
    *)
        echo "Error: Unknown build mode '$BUILD_MODE'. Use: all, debug, profile, release"
        exit 1
        ;;
esac

# Check if build was successful
BUILD_OUTPUT_DIR="build/host/outputs/repo"
if [[ ! -d "$BUILD_OUTPUT_DIR" ]]; then
    echo "Error: Build output directory not found: $BUILD_OUTPUT_DIR"
    exit 1
fi

# Create .m2 repository directory if it doesn't exist
mkdir -p "$M2_REPO"

echo ""
echo "=================================================="
echo "Publishing to Maven local: $M2_REPO"
echo "=================================================="

# Copy all files from build output to Maven local repository
cp -R "$BUILD_OUTPUT_DIR"/* "$M2_REPO/"

echo ""
echo ">>> Successfully published to Maven local!"
echo ""
echo "Artifacts location:"
echo "  - Build output: $BUILD_OUTPUT_DIR"
echo "  - Maven local:  $M2_REPO"
echo ""

# List the published artifacts
PACKAGE_PATH=$(find "$BUILD_OUTPUT_DIR" -name "*.aar" -type f | head -1 | xargs dirname 2>/dev/null || true)
if [[ -n "$PACKAGE_PATH" ]]; then
    GROUP_ID=$(echo "$PACKAGE_PATH" | sed "s|$BUILD_OUTPUT_DIR/||" | rev | cut -d'/' -f3- | rev | tr '/' '.')
    echo "Published artifacts with group: $GROUP_ID"
    echo ""
    
    # Show usage example
    echo "=================================================="
    echo "Usage in your Android project's build.gradle:"
    echo "=================================================="
    echo ""
    echo "repositories {"
    echo "    mavenLocal()"
    echo "    // ... other repositories"
    echo "}"
    echo ""
    echo "dependencies {"
    
    # List available artifacts
    for AAR_FILE in $(find "$BUILD_OUTPUT_DIR" -name "*-${BUILD_NUMBER}.aar" -type f); do
        ARTIFACT_PATH=$(dirname "$AAR_FILE")
        ARTIFACT_ID=$(basename "$ARTIFACT_PATH")
        echo "    implementation '$GROUP_ID:$ARTIFACT_ID:$BUILD_NUMBER'"
    done
    
    echo "}"
fi

echo ""
echo "Done!"

