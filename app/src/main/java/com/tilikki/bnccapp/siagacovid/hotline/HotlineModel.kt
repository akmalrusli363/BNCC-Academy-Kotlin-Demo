package com.tilikki.bnccapp.siagacovid.hotline

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class HotlineModel {
    private val okHttpClient: OkHttpClient = OkHttpClient()

    companion object {
        const val hotlineApiURL = "https://bncc-corona-versus.firebaseio.com/v1/hotlines.json"
    }

    fun fetchData(callback: Callback) {
        val request: Request = Request.Builder().url(hotlineApiURL).build()
        okHttpClient.newCall(request).enqueue(callback)
    }
}