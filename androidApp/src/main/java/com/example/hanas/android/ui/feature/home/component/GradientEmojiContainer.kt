package com.example.hanas.android.ui.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.hanas.android.extension.nonScaledSp
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun GradientEmojiContainer(
    modifier: Modifier = Modifier,
    emoji: String,
    color: Color,
    shape: Shape = RoundedCornerShape(16.dp),
) {
    Box(
        modifier =
            modifier
                .background(
                    brush =
                        Brush.verticalGradient(
                            colors = listOf(color, HanasTheme.colorScheme.gray100),
                            endY = 180f,
                        ),
                    shape = shape,
                ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = emoji,
            fontSize = 20.nonScaledSp(),
            lineHeight = TextUnit.Unspecified,
        )
    }
}
