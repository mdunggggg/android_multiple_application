package com.mdunggggg.core_util

sealed class Result<out V : Any, out E : Throwable> {

    fun isSuccess() = this !is Failure
    fun isFailure() = this is Failure

    fun getOrNull(): V? = when {
        isSuccess() -> (this as Success).value
        else -> null
    }

    fun get(): V {
        check(isSuccess()) { "Could not get value of Failure Result" }
        return (this as Success).value
    }

    fun exceptionOrNull(): E? = when {
        isFailure() -> (this as Failure).throwable
        else -> null
    }

    fun exception(): E {
        check(isFailure()) { "Could not get exception of Success Result" }
        return (this as Failure).throwable
    }

    class Failure<out E : Throwable> constructor(val throwable : E) : Result<Nothing, E>() {
        override fun toString(): String {
            return "[Failure: $throwable]"
        }
    }

    class Success<out V : Any> constructor(val value : V) : Result<V, Nothing>() {
        override fun toString(): String {
            return "[Success: $value]"
        }
    }

    companion object {
        fun <V : Any> success(value: V): Result<V, Nothing> = Success(value)
        fun <E : Throwable> failure(throwable: E): Result<Nothing, E> = Failure(throwable)
    }
}

suspend fun <V : Any> getResult(
    block: suspend () -> V
): Result<V, Throwable> {
    return try {
        Result.Success(block())
    } catch (e: Throwable) {
        Result.Failure(e)
    }
}
