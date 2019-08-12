package com.example.gottagetget.model

import com.example.gottagetget.model.source.KakaoApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by hclee on 2019-08-12.
 */

object ApiManager {
    val BASE_URL: String = "https://dapi.kakao.com/"
    val gson: Gson = GsonBuilder().setLenient().create()

    private val okHttpClientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    private val kakaoAdapter: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                okHttpClientBuilder.addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder().header(
                            "Authorization",
                            "KakaoAK 26fda27b78ec513941fb50234a9b0617"
                        ).build()
                    )
                }
                    .build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    val kakaoApi: KakaoApi = kakaoAdapter.create(KakaoApi::class.java)
}