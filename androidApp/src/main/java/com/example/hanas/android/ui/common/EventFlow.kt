package com.example.hanas.android.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

typealias EventFlow<T> = MutableSharedFlow<T>

@Composable
fun <T> rememberEventFlow(): EventFlow<T> {
    return remember {
        MutableSharedFlow(extraBufferCapacity = 20)
    }
}

@Composable
fun <T> EventEffect(
    eventFlow: SharedFlow<T>,
    block: suspend CoroutineScope.(T) -> Unit,
) {
    LaunchedEffect(eventFlow) {
        supervisorScope {
            eventFlow.collect { event ->
                withContext(Dispatchers.Main) {
                    block(event)
                }
            }
        }
    }
}
