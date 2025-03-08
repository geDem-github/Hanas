package com.example.hanas.module

import com.example.hanas.data.api.ApiClient
import com.example.hanas.data.repository.ChatGptRepositoryImpl
import com.example.hanas.domain.repository.ChatGptRepository
import com.example.hanas.domain.usecase.SendChatUseCase
import com.example.hanas.domain.usecase.SendChatUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModule =
    module {
        singleOf(::ChatGptRepositoryImpl) { bind<ChatGptRepository>() }
        factoryOf(::SendChatUseCaseImpl) { bind<SendChatUseCase>() }
        factory { ApiClient() }
    }
