package com.tilikki.bnccapp.siagacovid.overview

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class OverviewModel {
    private val okHttpClient: OkHttpClient = OkHttpClient()

    companion object {
        const val lookupSummaryApiURL = "https://api.kawalcorona.com/indonesia/"
    }

    fun fetchData(callback: Callback) {
        val request: Request = Request.Builder().url(lookupSummaryApiURL).build()
        okHttpClient.newCall(request).enqueue(callback)
    }
}