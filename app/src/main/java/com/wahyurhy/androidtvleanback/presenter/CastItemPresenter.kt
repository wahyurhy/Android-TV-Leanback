package com.wahyurhy.androidtvleanback.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wahyurhy.androidtvleanback.R
import com.wahyurhy.androidtvleanback.model.response.cast.Cast
import com.wahyurhy.androidtvleanback.utils.Common

class CastItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {

        val view = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.cast_item_view, parent, false)

//        val params = view.layoutParams
//        params.width = Common.getWidthInPercent(parent.context, 15)
//        params.height = Common.getHeightInPercent(parent.context, 21)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val content = item as Cast

        val imageView = viewHolder!!.view.findViewById<ImageView>(R.id.cast_img)

        val path = "https://www.themoviedb.org/t/p/w780" + content.profilePath
        Glide.with(viewHolder.view.context)
            .load(path)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }
}