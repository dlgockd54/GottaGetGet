package com.example.gottagetget.model.source

import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by hclee on 2019-08-12.
 */

interface KakaoApi {
    @GET("v2/search/image")
    fun getSearchedImageListSingle(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Single<JsonElement>
}