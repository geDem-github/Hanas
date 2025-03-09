package com.example.hanas.data.repository

import com.example.hanas.domain.entity.Message
import com.example.hanas.domain.repository.ChatHistoryRepository
import io.github.aakira.napier.Napier
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ChatHistoryRepositoryImpl : ChatHistoryRepository {
    private val history: MutableMap<Uuid, List<Message>> = mutableMapOf()

    override fun getHistory(uuid: Uuid): Result<List<Message>> {
        val foundHistory = history[uuid]
        return if (foundHistory != null) {
            Result.success(foundHistory)
        } else {
            Napier.e("ERROR", IllegalArgumentException())
            Result.failure(IllegalArgumentException())
        }
    }

    override fun saveHistory(
        target: Uuid,
        newHistory: List<Message>,
    ) {
        history[target] = newHistory
    }
}
