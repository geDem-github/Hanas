package com.example.hanas.android.ui.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun AvatarIcon(image: Painter) {
    Box(
        Modifier
            .background(HanasTheme.colorScheme.green, shape = CircleShape)
            .size(48.dp),
    )
}
