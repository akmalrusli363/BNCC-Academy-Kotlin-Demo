package com.tilikki.bnccapp.siagacovid.lookup.mvvm

import com.tilikki.bnccapp.siagacovid.lookup.LookupData
import okhttp3.Response
import org.json.JSONArray

class LookupModel {
    companion object {
        fun fetchFromJSON(response: Response) : List<LookupData> {
            val jsonString = response.body?.string()
            val jsonArray = JSONArray(jsonString)
            val lookupDataFromNetwork = mutableListOf<LookupData>()

            for (i in 0 until jsonArray.length()) {
                val attribute = jsonArray.getJSONObject(i).getJSONObject("attributes")
                lookupDataFromNetwork.add(
                    LookupData(
                        provinceID = attribute.getInt("Kode_Provi"),
                        provinceName = attribute.getString("Provinsi"),
                        numOfPositiveCase = attribute.getInt("Kasus_Posi"),
                        numOfRecoveredCase = attribute.getInt("Kasus_Semb"),
                        numOfDeathCase = attribute.getInt("Kasus_Meni")
                    )
                )
            }
            return lookupDataFromNetwork
        }
    }
}