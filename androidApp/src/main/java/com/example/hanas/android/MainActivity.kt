package com.example.hanas.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.hanas.module.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KoinApplication(
                application = {
                    modules(sharedModule)
                    androidContext(this@MainActivity)
                },
            ) {
                HanasApp()
            }
        }
    }
}
