package com.example.hanas.android.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hanas.android.ui.theme.HanasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    Scaffold(
        containerColor = HanasTheme.colorScheme.orange,
        topBar = {
            TopAppBar(
                title = {
                    Text("あああ")
                },
            )
        },
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            Text("ああ")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ChatScreen()
}
