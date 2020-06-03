package com.mba.mindvalley.shared.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mba.mindvalley.App
import com.mba.mindvalley.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiFactory {
    private var cacheSize = (10 * 1024 * 1024).toLong() // 10 MB

    private var cache: Cache = Cache(App.applicationContext().cacheDir, cacheSize)

    private val logging = HttpLoggingInterceptor()
        .apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    private var okHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(logging)
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://pastebin.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val coreApi: CoreApi = retrofit().create(CoreApi::class.java)
}