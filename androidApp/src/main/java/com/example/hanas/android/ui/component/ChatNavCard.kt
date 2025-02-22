package com.example.hanas.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.hanas.android.ui.theme.HanasTheme

data class ChatNavCardUiModel(
    val title: String,
    val icon: ImageVector,
    val backgroundGradientColor: List<Color>,
)

@Composable
fun ChatNavCard(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    gradientColors: List<Color>,
) {
    Column(
        modifier =
            modifier
                .heightIn(min = 110.dp)
                .background(
                    brush =
                        Brush.linearGradient(
                            colors = gradientColors,
                            start = Offset(x = -900f, y = 0f),
                        ),
                )
                .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier =
                Modifier
                    .size(40.dp),
            imageVector = icon,
            contentDescription = null,
            tint = HanasTheme.colorScheme.white,
        )

        Text(
            text = text,
            style = HanasTheme.typography.mBold,
            color = HanasTheme.colorScheme.white,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
