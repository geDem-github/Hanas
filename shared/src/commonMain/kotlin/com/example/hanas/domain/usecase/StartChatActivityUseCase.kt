package com.example.hanas.domain.usecase

import com.example.hanas.domain.entity.Message
import com.example.hanas.domain.repository.ChatActivityRepository
import com.example.hanas.domain.repository.ChatHistoryRepository
import com.example.hanas.domain.util.ChatPromptFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface StartChatActivityUseCase {
    /**
     * @return 最初に送信されるAIからのメッセージ
     */
    fun execute(chatId: String): Flow<Result<Message>>
}

@OptIn(ExperimentalUuidApi::class)
class StartChatActivityUseCaseImpl(
    private val chatActivityRepository: ChatActivityRepository,
    private val historyRepository: ChatHistoryRepository,
) : StartChatActivityUseCase {
    override fun execute(chatId: String): Flow<Result<Message>> =
        flow {
            val chatActivity =
                chatActivityRepository.get(Uuid.parse(chatId))
                    ?: run {
                        emit(Result.failure(Throwable()))
                        return@flow
                    }

            val prompt = ChatPromptFactory.createTalkRoomPrompt(chatActivity.title)
            val firstAiMessage = chatActivity.initialAiMessageOptions.random()

            historyRepository.saveHistory(Uuid.parse(chatId), listOf(prompt, firstAiMessage))

            println(historyRepository.getHistory(Uuid.parse(chatId)).getOrNull()?.map { it.content })

            emit(Result.success(firstAiMessage))
        }
}
