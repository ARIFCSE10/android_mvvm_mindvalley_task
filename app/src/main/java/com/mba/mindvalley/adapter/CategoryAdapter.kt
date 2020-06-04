package com.mba.mindvalley.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.mba.mindvalley.R
import com.mba.mindvalley.model.CategoryResponseDataCategory
import kotlinx.android.synthetic.main.category_list_item.view.*


class CategoryAdapter(private var activity: Activity) : BaseAdapter() {
    private var categories: MutableList<CategoryResponseDataCategory>? = null

    override fun getCount(): Int {
        return categories?.size ?: return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val grid: View
        if (convertView == null) {
            grid = activity.layoutInflater.inflate(R.layout.category_list_item, parent, false)
            grid.category_title_tv.text = categories?.get(position)?.name
        } else {
            grid = convertView
        }

        return grid
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    fun setData(data: List<CategoryResponseDataCategory>) {
        categories = data as MutableList<CategoryResponseDataCategory>
        notifyDataSetChanged()
    }
}