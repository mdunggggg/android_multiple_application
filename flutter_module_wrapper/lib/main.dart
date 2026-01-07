import 'package:flutter/material.dart';
import 'package:first_module_example/main.dart' as first_module_example;

@pragma('vm:entry-point')
void firstModuleExampleMain() => runApp(const first_module_example.FirstModuleExampleApp());

void main() => runApp(const FlutterModuleApp());

class FlutterModuleApp extends StatelessWidget {
  const FlutterModuleApp({super.key});
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: SizedBox(),
    );
  }
}
