package com.jeno.androidquest1

import kotlinx.coroutines.CancellationException


suspend fun <K, R> K.runSuspendCatching(
    request: suspend K.() -> R
): Result<R> {
    return try {
        Result.success(request())
    } catch (cancellationException: CancellationException) {
        throw cancellationException
    } catch (e: Exception) {
        Result.failure(e)
    }
}