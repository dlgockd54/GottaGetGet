package com.example.gottagetget.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gottagetget.model.ImageItem
import io.reactivex.Single

/**
 * Created by hclee on 2019-08-12.
 */

class ImageSearchViewModel : ViewModel() {
    fun getSearchedImageListSingle(query:  String): Single<List<ImageItem>> =
        Single.just(listOf())

    override fun onCleared() {

        super.onCleared()
    }
}