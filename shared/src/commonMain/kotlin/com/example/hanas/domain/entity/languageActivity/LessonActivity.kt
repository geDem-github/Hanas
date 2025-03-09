package com.example.hanas.domain.entity.languageActivity

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

// TODO: いまのとこあんまり決まってない
data class LessonActivity
    @OptIn(ExperimentalUuidApi::class)
    constructor(
        override val id: Uuid,
        override val title: String,
        override val emoji: String,
        override val color: Long,
        val status: LessonStatus,
        val introduction: String,
    ) : LanguageActivity

enum class LessonStatus {
    NotStarted,
    InProgress,
    Completed,
}
