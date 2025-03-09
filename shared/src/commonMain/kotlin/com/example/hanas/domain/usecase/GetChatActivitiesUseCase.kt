package com.example.hanas.domain.usecase

import com.example.hanas.domain.entity.languageActivity.ChatActivity
import com.example.hanas.domain.repository.ChatActivityRepository

interface GetChatActivitiesUseCase {
    fun execute(): List<ChatActivity>
}

class GetChatActivitiesUseCaseImpl(
    private val chatActivityRepository: ChatActivityRepository,
) : GetChatActivitiesUseCase {
    override fun execute(): List<ChatActivity> {
        return chatActivityRepository.getAll()
    }
}
