package com.wahyurhy.androidtvleanback

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.wahyurhy.androidtvleanback.model.Detail

class ItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false)

        val params = view.layoutParams
        params.width = getWidthInPercent(parent!!.context, 12)
        params.height = getHeightInPercent(parent!!.context, 32)

        return ViewHolder(view)
    }

    private fun getWidthInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    private fun getHeightInPercent(context: Context, percent: Int): Int {
        val height = context.resources.displayMetrics.heightPixels ?: 0
        return (height * percent) / 100
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