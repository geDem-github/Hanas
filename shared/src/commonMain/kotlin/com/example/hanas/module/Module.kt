package com.example.hanas.module

import com.example.hanas.repository.ChatGptRepository
import com.example.hanas.repository.ChatGptRepositoryImpl
import com.example.hanas.usecase.SendChatUseCase
import com.example.hanas.usecase.SendChatUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModule =
    module {
        singleOf(::ChatGptRepositoryImpl) { bind<ChatGptRepository>() }
        factoryOf(::SendChatUseCaseImpl) { bind<SendChatUseCase>() }
    }
