package com.example.hanas.domain.repository

import com.example.hanas.domain.entity.languageActivity.ChatActivity
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface ChatActivityRepository {
    fun getAll(): List<ChatActivity>

    fun get(id: Uuid): ChatActivity?
}
