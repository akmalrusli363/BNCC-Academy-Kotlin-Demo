package com.tilikki.bnccapp.siagacovid.hotline.mvvm

import android.app.Activity
import com.tilikki.bnccapp.siagacovid.hotline.HotlineActivity
import com.tilikki.bnccapp.siagacovid.hotline.HotlineAdapter
import com.tilikki.bnccapp.siagacovid.utils.AppEventLogging
import okhttp3.*
import java.io.IOException

class HotlineViewModel (val activity: Activity) {
    private val okHttpClient = OkHttpClient()

    companion object {
        private const val hotlineApiURL = "https://bncc-corona-versus.firebaseio.com/v1/hotlines.json"
    }

    fun fetchData(hotlineAdapter: HotlineAdapter) {
        val request: Request = Request.Builder().url(hotlineApiURL).build()
        okHttpClient.newCall(request).enqueue(getCallback(hotlineAdapter))
    }

    private fun getCallback(hotlineAdapter: HotlineAdapter): Callback {
        return object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                AppEventLogging(activity).logExceptionOnToast(e)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val hotlineListFromNetwork = HotlineModel.fetchFromJSON(response)

                    activity.runOnUiThread {
                        hotlineAdapter.updateData(hotlineListFromNetwork)
                    }
                } catch (e: Exception) {
                    AppEventLogging(activity).logExceptionOnToast(e)
                }
            }

        }
    }
}