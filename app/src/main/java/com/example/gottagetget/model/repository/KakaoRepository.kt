package com.example.gottagetget.model.repository

import com.example.gottagetget.model.ApiManager
import com.example.gottagetget.model.ImageItem
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by hclee on 2019-08-12.
 */

object KakaoRepository {
    fun getSearchImageListSingle(query: String, page: Int): Single<List<ImageItem>> =
        ApiManager.kakaoApi.getSearchedImageListSingle(query, page)
            .map {
                it.asJsonObject.getAsJsonArray("documents")
                    .map {
                        ApiManager.gson.fromJson(it, ImageItem::class.java)
                    }
            }
            .subscribeOn(Schedulers.io())
}