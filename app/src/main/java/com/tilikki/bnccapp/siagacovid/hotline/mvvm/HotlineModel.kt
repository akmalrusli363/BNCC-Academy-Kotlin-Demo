package com.tilikki.bnccapp.siagacovid.hotline.mvvm

import com.tilikki.bnccapp.siagacovid.hotline.HotlineData
import okhttp3.Response
import org.json.JSONArray

class HotlineModel {
    companion object {
        fun fetchFromJSON(response: Response) : List<HotlineData> {
            val jsonString = response.body?.string()
            val jsonArray = JSONArray(jsonString)
            val hotlineListFromNetwork = mutableListOf<HotlineData>()

            for (i in 0 until jsonArray.length()) {
                hotlineListFromNetwork.add(
                    HotlineData(
                        imgIcon = jsonArray.getJSONObject(i).getString("img_icon"),
                        name = jsonArray.getJSONObject(i).getString("name"),
                        phone = jsonArray.getJSONObject(i).getString("phone")
                    )
                )
            }

            return hotlineListFromNetwork
        }
    }
}