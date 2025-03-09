package com.example.hanas.module

import com.example.hanas.data.api.ApiClient
import com.example.hanas.data.repository.ChatActivityRepositoryImpl
import com.example.hanas.data.repository.ChatGptRepositoryImpl
import com.example.hanas.data.repository.ChatHistoryRepositoryImpl
import com.example.hanas.data.repository.LessonActivityRepositoryImpl
import com.example.hanas.domain.repository.ChatActivityRepository
import com.example.hanas.domain.repository.ChatGptRepository
import com.example.hanas.domain.repository.ChatHistoryRepository
import com.example.hanas.domain.repository.LessonActivityRepository
import com.example.hanas.domain.usecase.GetChatActivitiesUseCase
import com.example.hanas.domain.usecase.GetChatActivitiesUseCaseImpl
import com.example.hanas.domain.usecase.GetChatActivityUseCase
import com.example.hanas.domain.usecase.GetChatActivityUseCaseImpl
import com.example.hanas.domain.usecase.SendChatUseCase
import com.example.hanas.domain.usecase.SendChatUseCaseImpl
import com.example.hanas.domain.usecase.StartChatActivityUseCase
import com.example.hanas.domain.usecase.StartChatActivityUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModule =
    module {
        // Repository
        factoryOf(::ChatGptRepositoryImpl) { bind<ChatGptRepository>() }
        singleOf(::ChatHistoryRepositoryImpl) { bind<ChatHistoryRepository>() }
        singleOf(::ChatActivityRepositoryImpl) { bind<ChatActivityRepository>() }
        factoryOf(::LessonActivityRepositoryImpl) { bind<LessonActivityRepository>() }
        // UseCase
        factoryOf(::GetChatActivitiesUseCaseImpl) { bind<GetChatActivitiesUseCase>() }
        factoryOf(::SendChatUseCaseImpl) { bind<SendChatUseCase>() }
        factoryOf(::GetChatActivityUseCaseImpl) { bind<GetChatActivityUseCase>() }
        factoryOf(::StartChatActivityUseCaseImpl) { bind<StartChatActivityUseCase>() }
        // Others
        factory { ApiClient() }
    }
