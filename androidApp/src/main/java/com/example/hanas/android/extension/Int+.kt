package com.example.hanas.android.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun Int.nonScaledSp(): TextUnit {
    val value = this
    val density = LocalDensity.current

    with(density) {
        return value.dp.toSp()
    }
}
