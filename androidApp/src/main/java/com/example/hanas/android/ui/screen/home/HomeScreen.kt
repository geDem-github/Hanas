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
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun HomeScreen(onClickChatNavCard: () -> Unit) {
    val haptic = LocalHapticFeedback.current
    val chatNavCards =
        listOf(
            ChatNavCardUiModel(
                "フリートーク",
                "🚌",
                HanasTheme.colorScheme.green,
            ),
            ChatNavCardUiModel(
                "キャンプ",
                "🐶",
                HanasTheme.colorScheme.pink,
            ),
            ChatNavCardUiModel(
                "趣味",
                "✈️",
                HanasTheme.colorScheme.orange,
            ),
            ChatNavCardUiModel(
                "自己紹介",
                "🍤",
                HanasTheme.colorScheme.blue,
            ),
            ChatNavCardUiModel(
                "自己紹介",
                "🍪",
                HanasTheme.colorScheme.purple,
            ),
        )
    val lessonNavCards =
        listOf(
            LessonNavCardUiModel(
                title = "今日のレッスン①",
                emoji = "🍪",
                status = LessonNavCardStatus.InProgress,
                color = HanasTheme.colorScheme.pink,
            ),
            LessonNavCardUiModel(
                title = "今日のレッスン②",
                emoji = "\uD83C\uDF64",
                status = LessonNavCardStatus.Completed,
                color = HanasTheme.colorScheme.orange,
            ),
            LessonNavCardUiModel(
                title = "旅行で使える言い回し",
                emoji = "\uD83D\uDC36",
                status = LessonNavCardStatus.NotStarted,
                color = HanasTheme.colorScheme.purple,
            ),
        )

    Column(
        Modifier
            .background(HanasTheme.colorScheme.primaryBackground)
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(vertical = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(Int.MAX_VALUE.dp),
                        ambientColor = HanasTheme.colorScheme.shadow,
                        spotColor = HanasTheme.colorScheme.shadow,
                    )
                    .background(
                        color = HanasTheme.colorScheme.secondaryBackground,
                        shape = RoundedCornerShape(Int.MAX_VALUE.dp),
                    )
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
                    "Welcome back!👋",
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
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    chatNavCards.chunked(2).forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
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
                                                onClickChatNavCard()
                                            },
                                    uiModel = rowItem,
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
                        .background(HanasTheme.colorScheme.tertiaryBackground),
                title = "学習",
                emoji = "📔",
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    lessonNavCards.forEach { lessonNavCard ->
                        LessonNavCard(
                            modifier =
                                Modifier
                                    .shadow(
                                        elevation = 4.dp,
                                        shape = RoundedCornerShape(8.dp),
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
                .padding(horizontal = 12.dp)
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
        HomeScreen({})
    }
}
