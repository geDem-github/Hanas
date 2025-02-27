package com.example.hanas.android.ui.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.hanas.android.ui.theme.HanasTheme

data class TopBarActionButton(
    val icon: ImageVector,
    val onClick: () -> Unit,
)

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    leftActionButton: TopBarActionButton? = null,
    rightActionButton: TopBarActionButton? = null,
) {
    val borderColor = HanasTheme.colorScheme.border
    Row(
        modifier =
            modifier
                .height(60.dp)
                .fillMaxWidth()
                .drawWithContent {
                    drawContent()

                    drawLine(
                        color = borderColor,
                        strokeWidth = Stroke.HairlineWidth,
                        start = Offset(x = 0f, y = this.size.height),
                        end = Offset(x = this.size.width, y = this.size.height),
                    )
                }
                .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TopBarIcon(
            modifier = Modifier.clickable { leftActionButton?.onClick?.invoke() },
            imageVector = leftActionButton?.icon,
        )

        Text(
            text = title,
            style = HanasTheme.typography.lRegular,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        TopBarIcon(
            modifier = Modifier.clickable { rightActionButton?.onClick?.invoke() },
            imageVector = rightActionButton?.icon,
        )
    }
}

@Composable
private fun TopBarIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector?,
) {
    Box(
        modifier =
            modifier
                .size(48.dp),
        contentAlignment = Alignment.Center,
    ) {
        imageVector?.also { imageVector ->
            Icon(
                modifier = Modifier,
                imageVector = imageVector,
                contentDescription = null,
                tint = HanasTheme.colorScheme.icon,
            )
        }
    }
}
