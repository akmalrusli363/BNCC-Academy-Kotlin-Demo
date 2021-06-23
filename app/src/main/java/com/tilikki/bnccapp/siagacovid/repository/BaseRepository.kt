package com.tilikki.bnccapp.siagacovid.repository

import com.squareup.moshi.Moshi
import com.tilikki.bnccapp.siagacovid.utils.jsonadapter.MoshiDateAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class BaseRepository {
    private val moshi = Moshi.Builder().add(MoshiDateAdapter()).build()
    protected val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(getBaseUrl())
            .build()
    }

    abstract fun getBaseUrl(): String
}
