package com.example.gottagetget.model.repository

import com.example.gottagetget.model.ApiManager
import com.example.gottagetget.model.SearchResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by hclee on 2019-08-12.
 */

object KakaoRepository {
    fun getSearchImageListSingle(query: String, page: Int, size: Int): Single<SearchResponse> =
        ApiManager.kakaoApi.getSearchedImageListSingle(query, page, size)
            .subscribeOn(Schedulers.io())
}