package com.example.hanas.android.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hanas.android.ui.component.AvatarIcon
import com.example.hanas.android.ui.component.ChatNavCard
import com.example.hanas.android.ui.component.ChatNavCardUiModel
import com.example.hanas.android.ui.component.LessonNavCard
import com.example.hanas.android.ui.component.LessonNavCardStatus
import com.example.hanas.android.ui.component.LessonNavCardUiModel
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun HomeScreen() {
    val haptic = LocalHapticFeedback.current
    val chatNavCards =
        listOf(
            ChatNavCardUiModel(
                "フリートーク",
                Icons.Outlined.Email,
                listOf(HanasTheme.colorScheme.gray300, HanasTheme.colorScheme.gray600),
            ),
            ChatNavCardUiModel(
                "キャンプ",
                Icons.Outlined.Home,
                listOf(HanasTheme.colorScheme.lightYellow, HanasTheme.colorScheme.yellow),
            ),
            ChatNavCardUiModel(
                "趣味",
                Icons.Outlined.Delete,
                listOf(HanasTheme.colorScheme.lightBlue, HanasTheme.colorScheme.blue),
            ),
            ChatNavCardUiModel(
                "自己紹介",
                Icons.Outlined.KeyboardArrowDown,
                listOf(HanasTheme.colorScheme.lightPink, HanasTheme.colorScheme.pink),
            ),
            ChatNavCardUiModel(
                "自己紹介",
                Icons.Outlined.LocationOn,
                listOf(HanasTheme.colorScheme.lightGreen, HanasTheme.colorScheme.green),
            ),
        )
    val lessonNavCards =
        listOf(
            LessonNavCardUiModel(
                title = "今日のレッスン①",
                icon = Icons.Default.Home,
                status = LessonNavCardStatus.InProgress,
                color = HanasTheme.colorScheme.pink,
            ),
            LessonNavCardUiModel(
                title = "今日のレッスン②",
                icon = Icons.Default.CheckCircle,
                status = LessonNavCardStatus.Completed,
                color = HanasTheme.colorScheme.orange,
            ),
            LessonNavCardUiModel(
                title = "旅行で使える言い回し",
                icon = Icons.Default.Add,
                status = LessonNavCardStatus.NotStarted,
                color = HanasTheme.colorScheme.purple,
            ),
        )

    Column(
        Modifier
            .background(HanasTheme.colorScheme.background)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(vertical = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
                    .clip(shape = RoundedCornerShape(Int.MAX_VALUE.dp))
                    .background(HanasTheme.colorScheme.secondaryBackground)
                    .clickable {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    }
                    .padding(horizontal = 12.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AvatarIcon(painterResource(id = android.R.drawable.ic_menu_gallery))

            Column {
                Text("Tutor", style = HanasTheme.typography.xsRegular)
                Text(
                    "Welcome back!",
                    style = HanasTheme.typography.mBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Column {
            SectionContainer(
                modifier = Modifier,
                title = "AIチャット",
                emoji = "\uD83D\uDDE3\uFE0F",
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    chatNavCards.chunked(2).forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            rowItems.forEach { rowItem ->
                                ChatNavCard(
                                    modifier =
                                        Modifier
                                            .weight(1f)
                                            .shadow(
                                                elevation = 8.dp,
                                                ambientColor = HanasTheme.colorScheme.shadow,
                                                spotColor = HanasTheme.colorScheme.shadow,
                                                shape = RoundedCornerShape(12.dp),
                                            )
                                            .clickable {
                                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                            },
                                    text = rowItem.title,
                                    icon = rowItem.icon,
                                    gradientColors = rowItem.backgroundGradientColor,
                                )

                                if (rowItems.size < 2) {
                                    Spacer(modifier = Modifier.weight(1f)) // 奇数要素の場合のスペース
                                }
                            }
                        }
                    }
                }
            }

            SectionContainer(
                modifier =
                    Modifier
                        .background(HanasTheme.colorScheme.secondaryBackground),
                title = "学習",
                emoji = "📔",
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    lessonNavCards.forEach { lessonNavCard ->
                        LessonNavCard(
                            modifier =
                                Modifier
                                    .shadow(
                                        elevation = 2.dp,
                                        shape = RoundedCornerShape(4.dp),
                                        ambientColor = HanasTheme.colorScheme.shadow,
                                        spotColor = HanasTheme.colorScheme.shadow,
                                    )
                                    .clickable {
                                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    },
                            uiModel = lessonNavCard,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionContainer(
    modifier: Modifier = Modifier,
    title: String,
    emoji: String,
    content: @Composable () -> Unit,
) {
    Column(
        modifier =
            modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Text(
            text = "$emoji$title",
            style = HanasTheme.typography.xlBold,
        )

        content()
    }
}

@Preview
@Composable
private fun Preview() {
    HanasTheme {
        HomeScreen()
    }
}
