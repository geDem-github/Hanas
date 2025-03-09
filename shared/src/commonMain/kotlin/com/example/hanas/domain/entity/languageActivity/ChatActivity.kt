package com.example.hanas.domain.entity.languageActivity

import com.example.hanas.domain.entity.Message
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ChatActivity
    @OptIn(ExperimentalUuidApi::class)
    constructor(
        override val id: Uuid,
        override val title: String,
        override val emoji: String,
        override val color: Long,
        val initialAiMessageOptions: List<Message>,
    ) : LanguageActivity
