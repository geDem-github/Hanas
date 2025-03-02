package com.example.hanas.android.util.mic

import android.content.Intent
import android.speech.RecognizerIntent

object HanasIntentFactory {
    val englishOnlySpeechRecognizerIntent: Intent
        get() =
            Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                .apply {
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en_EN")
                }
}
