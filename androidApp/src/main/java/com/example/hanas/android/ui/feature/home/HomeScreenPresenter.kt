package com.example.hanas.android.ui.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.hanas.android.ui.common.EventEffect
import com.example.hanas.android.ui.feature.chat.navigation.ChatScreenDestination
import com.example.hanas.android.ui.feature.home.component.ChatNavCardUiModel
import com.example.hanas.android.ui.feature.home.component.LessonNavCardStatus
import com.example.hanas.android.ui.feature.home.component.LessonNavCardUiModel
import com.example.hanas.android.ui.theme.HanasTheme
import com.example.hanas.domain.usecase.GetChatActivitiesUseCase
import kotlinx.coroutines.flow.SharedFlow
import org.koin.compose.koinInject
import kotlin.uuid.ExperimentalUuidApi

sealed interface HomeScreenEvent {
    data object OnAppear : HomeScreenEvent

    data class OnSelectChat(val id: String) : HomeScreenEvent
}

data class HomeUiState(
    val chatNavCards: List<ChatNavCardUiModel>,
    val lessonNavCards: List<LessonNavCardUiModel>,
)

@OptIn(ExperimentalUuidApi::class)
@Composable
internal fun homeScreenPresenter(
    navController: NavController,
    eventFlow: SharedFlow<HomeScreenEvent>,
    getChatActivitiesUseCase: GetChatActivitiesUseCase = koinInject(),
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
                val chatActivities = getChatActivitiesUseCase.execute()
                setUiState(
                    uiState.copy(
                        chatNavCards =
                            chatActivities.map {
                                ChatNavCardUiModel(
                                    id = it.id.toString(),
                                    title = it.title,
                                    emoji = it.emoji,
                                    color = Color(it.color),
                                )
                            },
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
                navController.navigate(ChatScreenDestination(event.id)) {
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
