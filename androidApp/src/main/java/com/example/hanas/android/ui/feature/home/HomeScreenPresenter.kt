package com.example.hanas.android.ui.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.hanas.android.ui.common.EventEffect
import com.example.hanas.android.ui.feature.chat.navigation.ChatScreenDestination
import com.example.hanas.android.ui.feature.home.component.ChatNavCardUiModel
import com.example.hanas.android.ui.feature.home.component.LessonNavCardStatus
import com.example.hanas.android.ui.feature.home.component.LessonNavCardUiModel
import com.example.hanas.android.ui.theme.HanasTheme
import kotlinx.coroutines.flow.SharedFlow
import java.util.UUID

sealed interface HomeScreenEvent {
    data object OnAppear : HomeScreenEvent

    data class OnSelectChat(val id: UUID) : HomeScreenEvent
}

data class HomeUiState(
    val chatNavCards: List<ChatNavCardUiModel>,
    val lessonNavCards: List<LessonNavCardUiModel>,
)

@Composable
internal fun homeScreenPresenter(
    navController: NavController,
    eventFlow: SharedFlow<HomeScreenEvent>,
): HomeUiState {
    val (uiState, setUiState) =
        remember {
            mutableStateOf(
                HomeUiState(
                    chatNavCards = emptyList(),
                    lessonNavCards = emptyList(),
                ),
            )
        }
    val colorScheme = HanasTheme.colorScheme

    EventEffect(eventFlow) { event ->
        when (event) {
            is HomeScreenEvent.OnAppear -> {
                setUiState(
                    uiState.copy(
                        chatNavCards =
                            listOf(
                                ChatNavCardUiModel(
                                    "フリートーク",
                                    "🚌",
                                    colorScheme.green,
                                ),
                                ChatNavCardUiModel(
                                    "キャンプ",
                                    "🐶",
                                    colorScheme.pink,
                                ),
                                ChatNavCardUiModel(
                                    "趣味",
                                    "✈️",
                                    colorScheme.orange,
                                ),
                                ChatNavCardUiModel(
                                    "自己紹介",
                                    "🍤",
                                    colorScheme.blue,
                                ),
                                ChatNavCardUiModel(
                                    "自己紹介",
                                    "🍪",
                                    colorScheme.purple,
                                ),
                            ),
                        lessonNavCards =
                            listOf(
                                LessonNavCardUiModel(
                                    title = "今日のレッスン①",
                                    emoji = "🍪",
                                    status = LessonNavCardStatus.InProgress,
                                    color = colorScheme.pink,
                                ),
                                LessonNavCardUiModel(
                                    title = "今日のレッスン②",
                                    emoji = "\uD83C\uDF64",
                                    status = LessonNavCardStatus.Completed,
                                    color = colorScheme.orange,
                                ),
                                LessonNavCardUiModel(
                                    title = "旅行で使える言い回し",
                                    emoji = "\uD83D\uDC36",
                                    status = LessonNavCardStatus.NotStarted,
                                    color = colorScheme.purple,
                                ),
                            ),
                    ),
                )
            }

            is HomeScreenEvent.OnSelectChat -> {
                navController.navigate(ChatScreenDestination) {
                    popUpTo(checkNotNull(navController.graph.findStartDestination().route)) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }

    return uiState
}
