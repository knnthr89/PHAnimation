package com.example.phanimation

import kotlinx.coroutines.delay

suspend fun <T> ListIterator<T>.doWhenHasNextOrPrevious(
    delayMillis : Long = 3000,
    doWork : suspend (T) -> Unit
) {
    while (hasNext() || hasPrevious()) {
        while (hasNext()){
            delay(delayMillis)
            doWork(next())
        }
        while (hasPrevious()){
            delay(delayMillis)
            doWork(previous())
        }
    }
}