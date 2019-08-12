package com.example.gottagetget.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gottagetget.R
import com.example.gottagetget.model.ImageItem
import com.example.gottagetget.view.adapter.ImageAdapter
import com.example.gottagetget.viewmodel.ImageSearchViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_image_search.*
import java.util.concurrent.TimeUnit

class ImageSearchActivity : AppCompatActivity() {
    companion object {
        val TAG: String = ImageSearchActivity::class.java.simpleName
    }

    private lateinit var mImageAdapter: ImageAdapter
    private lateinit var mImageSearchViewModel: ImageSearchViewModel
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mTextChangeSubject: PublishSubject<String> = PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_search)

        init()
    }

    private fun init() {
        mImageAdapter = ImageAdapter(this@ImageSearchActivity, mutableListOf())
        mImageSearchViewModel = ViewModelProviders.of(this@ImageSearchActivity).get(ImageSearchViewModel::class.java)

        rv_image_list.apply {
            adapter = mImageAdapter
            layoutManager = LinearLayoutManager(this@ImageSearchActivity)
        }

        mCompositeDisposable.add(
            mTextChangeSubject
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<String>() {
                    override fun onNext(t: String) {
                        pullSearchedImageList(t)
                    }

                    override fun onError(e: Throwable) { }
                    override fun onComplete() { }
                })
        )

        edittext_image_search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mTextChangeSubject.onNext(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    private fun pullSearchedImageList(query: String, page: Int = 1) {
        mCompositeDisposable.add(
            getSearchedImageListSingle(query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<ImageItem>>() {
                    override fun onSuccess(t: List<ImageItem>) {
                        with(mImageAdapter) {
                            if(t.isNotEmpty()) {
                                mSearchedImageList.clear()
                                mSearchedImageList.addAll(t)
                                notifyDataSetChanged()

                                tv_empty_search_result.visibility = View.GONE
                                rv_image_list.visibility = View.VISIBLE
                            } else {
                                rv_image_list.visibility = View.GONE
                                tv_empty_search_result.visibility = View.VISIBLE
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(this@ImageSearchActivity, getString(R.string.error_search), Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        )
    }

    private fun getSearchedImageListSingle(query: String, page: Int) : Single<List<ImageItem>> =
        mImageSearchViewModel.getSearchedImageListSingle(query, page)

    override fun onDestroy() {
        mCompositeDisposable.clear()

        super.onDestroy()
    }
}
