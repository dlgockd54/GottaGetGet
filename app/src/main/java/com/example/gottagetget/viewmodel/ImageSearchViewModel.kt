package com.example.gottagetget.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gottagetget.model.SearchResponse
import com.example.gottagetget.model.repository.KakaoRepository
import io.reactivex.Single

/**
 * Created by hclee on 2019-08-12.
 */

class ImageSearchViewModel : ViewModel() {
    fun getSearchedImageListSingle(query:  String, page: Int, size: Int): Single<SearchResponse> =
        KakaoRepository.getSearchImageListSingle(query, page, size)

    override fun onCleared() {

        super.onCleared()
    }
}