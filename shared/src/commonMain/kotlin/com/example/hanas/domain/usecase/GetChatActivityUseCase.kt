package com.example.hanas.domain.usecase

import com.example.hanas.domain.entity.languageActivity.ChatActivity
import com.example.hanas.domain.repository.ChatActivityRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface GetChatActivityUseCase {
    fun execute(id: String): ChatActivity?
}

@OptIn(ExperimentalUuidApi::class)
class GetChatActivityUseCaseImpl(
    private val chatActivityRepository: ChatActivityRepository,
) : GetChatActivityUseCase {
    override fun execute(id: String): ChatActivity? {
        return chatActivityRepository.get(Uuid.parse(id))
    }
}
