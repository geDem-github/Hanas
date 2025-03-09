package com.example.hanas.data.repository

import com.example.hanas.domain.entity.languageActivity.LessonActivity
import com.example.hanas.domain.repository.LessonActivityRepository

class LessonActivityRepositoryImpl : LessonActivityRepository {
    override fun getAll(): List<LessonActivity> {
        return emptyList()
    }
}
