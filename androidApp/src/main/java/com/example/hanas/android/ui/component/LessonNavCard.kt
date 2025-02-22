package com.example.hanas.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.hanas.android.ui.theme.HanasTheme

data class LessonNavCardUiModel(
    val title: String,
    val icon: ImageVector,
    val status: LessonNavCardStatus,
    val color: Color,
)

enum class LessonNavCardStatus {
    NotStarted,
    InProgress,
    Completed,
    ;

    val text: String
        get() =
            when (this) {
                NotStarted -> "NEW"
                InProgress -> "学習中"
                Completed -> "完了"
            }

    val color: Color
        @Composable
        get() =
            when (this) {
                NotStarted -> HanasTheme.colorScheme.green
                InProgress -> HanasTheme.colorScheme.blue
                Completed -> HanasTheme.colorScheme.yellow
            }
}

@Composable
fun LessonNavCard(
    modifier: Modifier = Modifier,
    uiModel: LessonNavCardUiModel,
) {
    Box(
        modifier =
            modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(HanasTheme.colorScheme.background)
                .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .size(44.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = CircleShape,
                            ambientColor = HanasTheme.colorScheme.shadow,
                            spotColor = HanasTheme.colorScheme.shadow,
                        )
                        .background(uiModel.color, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = uiModel.icon,
                    contentDescription = null,
                    tint = HanasTheme.colorScheme.text,
                )
            }

            Text(
                modifier = Modifier.weight(1f),
                text = uiModel.title,
                style = HanasTheme.typography.mBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                modifier =
                    Modifier.background(
                        color = uiModel.status.color,
                        shape = RoundedCornerShape(Int.MAX_VALUE.dp),
                    )
                        .padding(horizontal = 4.dp, vertical = 2.dp),
                text = uiModel.status.text,
                style = HanasTheme.typography.xsBold,
                color = HanasTheme.colorScheme.white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
