package com.tilikki.bnccapp.siagacovid.lookup.mvvm

import android.app.Activity
import com.tilikki.bnccapp.siagacovid.lookup.LookupAdapter
import com.tilikki.bnccapp.siagacovid.utils.AppEventLogging
import okhttp3.*
import java.io.IOException

class LookupViewModel(val activity: Activity) {
    private val okHttpClient = OkHttpClient()

    companion object {
        private const val lookupDataApiURL = "https://api.kawalcorona.com/indonesia/provinsi/"
    }

    fun fetchData(lookupAdapter: LookupAdapter) {
        val request: Request = Request.Builder().url(lookupDataApiURL).build()

        okHttpClient.newCall(request).enqueue(getCallback(lookupAdapter))
    }

    private fun getCallback(lookupAdapter: LookupAdapter): Callback {
        return object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                AppEventLogging(activity).logExceptionOnToast(e)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val lookupData = LookupModel.fetchFromJSON(response)

                    activity.runOnUiThread {
                        lookupAdapter.updateData(lookupData)
                    }
                } catch (e: Exception) {
                    AppEventLogging(activity).logExceptionOnToast(e)
                }
            }
        }
    }
}