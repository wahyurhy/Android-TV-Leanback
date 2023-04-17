package com.wahyurhy.androidtvleanback.presenter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.wahyurhy.androidtvleanback.R
import com.wahyurhy.androidtvleanback.model.Detail
import com.wahyurhy.androidtvleanback.utils.Common
import com.wahyurhy.androidtvleanback.utils.Common.Companion.getHeightInPercent
import com.wahyurhy.androidtvleanback.utils.Common.Companion.getWidthInPercent

class ItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_view, parent, false)

        val params = view.layoutParams
        params.width = getWidthInPercent(parent.context, 12)
        params.height = getHeightInPercent(parent.context, 32)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val context = item as? Detail

        val imageView = viewHolder?.view?.findViewById<ImageView>(R.id.poster_image)


        val url = "https://www.themoviedb.org/t/p/w500" + context?.posterPath
        Glide.with(viewHolder?.view?.context!!)
            .load(url)
            .into(imageView!!)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }

}