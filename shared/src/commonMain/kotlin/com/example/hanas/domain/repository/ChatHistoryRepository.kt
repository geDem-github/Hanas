package com.example.hanas.domain.repository

import com.example.hanas.domain.entity.Message
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface ChatHistoryRepository {
    fun getHistory(uuid: Uuid): Result<List<Message>>

    fun saveHistory(
        target: Uuid,
        newHistory: List<Message>,
    )
}
