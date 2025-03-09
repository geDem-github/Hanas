package com.example.hanas.domain.entity.languageActivity

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal interface LanguageActivity {
    @OptIn(ExperimentalUuidApi::class)
    val id: Uuid
    val title: String
    val emoji: String
    val color: Long
}
