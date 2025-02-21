package com.example.hanas.android.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun HomeScreen() {
    Column(Modifier.background(HanasTheme.colorScheme.darkGray).fillMaxSize()) {
        Text("Hello, world!", style = HanasTheme.typography.xlBold)
        Text("Hello, world!", style = HanasTheme.typography.lBold)
        Text("Hello, world!", style = HanasTheme.typography.mBold)
        Text("Hello, world!", style = HanasTheme.typography.sBold)
        Text("Hello, world!", style = HanasTheme.typography.xsBold)

        Text("Hello, world!", style = HanasTheme.typography.xlRegular)
        Text("Hello, world!", style = HanasTheme.typography.lRegular)
        Text("Hello, world!", style = HanasTheme.typography.mRegular)
        Text("Hello, world!", style = HanasTheme.typography.sRegular)
        Text("Hello, world!", style = HanasTheme.typography.xsRegular)
    }
}