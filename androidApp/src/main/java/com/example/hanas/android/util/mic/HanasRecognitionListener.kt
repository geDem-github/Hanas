package com.example.hanas.android.util.mic

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

class HanasRecognitionListener(
    private val onResult: (String?) -> Unit,
) : RecognitionListener {
    // 2度結果を返さないための状態
    private var isAlreadyEnded: Boolean = false

    private fun startListening() {
        isAlreadyEnded = false
    }

    private fun endListening(result: String?) {
        if (isAlreadyEnded) return
        onResult(result)
        isAlreadyEnded = true
    }

    // 認識開始時
    override fun onBeginningOfSpeech() {
        startListening()
    }

    // 認識終了時
    override fun onResults(p0: Bundle?) {
        val stringArray = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.getOrNull(0)
        endListening(if (stringArray.isNullOrEmpty()) null else stringArray.toString())
    }

    // エラー発生時
    override fun onError(p0: Int) {
        endListening(null)
    }

    override fun onReadyForSpeech(p0: Bundle?) {}

    override fun onRmsChanged(p0: Float) {}

    override fun onBufferReceived(p0: ByteArray?) {}

    override fun onEndOfSpeech() {}

    override fun onPartialResults(p0: Bundle?) {}

    override fun onEvent(
        p0: Int,
        p1: Bundle?,
    ) {}
}
