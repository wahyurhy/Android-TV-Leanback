package com.wahyurhy.androidtvleanback.fragment

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.wahyurhy.androidtvleanback.ItemPresenter
import com.wahyurhy.androidtvleanback.model.DataModel
import com.wahyurhy.androidtvleanback.model.Detail

class ListFragment : RowsSupportFragment() {

    private var itemSelectedListener: ((Detail) -> Unit)? = null
    private var itemClickListener: ((Detail) -> Unit)? = null

    private var rootAdapter: ArrayObjectAdapter =
        ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = rootAdapter

        onItemViewSelectedListener = ItemViewSelectedListener()
        onItemViewClickedListener = ItemViewClickedListener()
    }

    fun bindData(dataList: DataModel) {

        dataList.result.forEachIndexed { index, result ->
            val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())

            result.details.forEach {
                arrayObjectAdapter.add(it)
            }

            val headerItem = HeaderItem(result.title)
            val listRow = ListRow(headerItem, arrayObjectAdapter)
            rootAdapter.add(listRow)
        }

    }

    fun setOnContentSelectedListener(listener: (Detail) -> Unit) {
        this.itemSelectedListener = listener
    }

    fun setOnContentClickedListener(listener: (Detail) -> Unit) {
        this.itemClickListener = listener
    }

    inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is Detail) {
                itemSelectedListener?.invoke(item)
            }
        }

    }

    inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is Detail) {
                itemClickListener?.invoke(item)
            }
        }

    }
}