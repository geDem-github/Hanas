package com.example.hanas.domain.repository

import com.example.hanas.domain.entity.languageActivity.LessonActivity

interface LessonActivityRepository {
    fun getAll(): List<LessonActivity>
}
