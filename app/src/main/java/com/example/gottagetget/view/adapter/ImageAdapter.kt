package com.example.gottagetget.view.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gottagetget.R
import com.example.gottagetget.model.ImageItem
import kotlinx.android.synthetic.main.item_image.view.*

/**
 * Created by hclee on 2019-08-12.
 */

class ImageAdapter(val mActivity: Activity, val mSearchedImageList: MutableList<ImageItem>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))

    override fun getItemCount(): Int = mSearchedImageList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Log.d("ImageAdapter", "onBindViewHolder()")

        with(mSearchedImageList[position]) {
            Glide.with(mActivity)
                .load(image_url)
                .into(holder.mImageView)
        }
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView = itemView.iv_searched_image
    }
}