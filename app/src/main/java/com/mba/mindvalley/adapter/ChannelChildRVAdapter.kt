package com.mba.mindvalley.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mba.mindvalley.R
import com.mba.mindvalley.model.Channel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.channel_course_list_item.view.*
import kotlinx.android.synthetic.main.channel_series_list_item.view.*

class ChannelChildRVAdapter(private var activity: Activity, private var isCourse: Boolean) :
    RecyclerView.Adapter<ChannelChildRVAdapter.ViewHolder>() {

    private var channel: Channel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = if (isCourse) {
            LayoutInflater.from(activity).inflate(R.layout.channel_course_list_item, parent, false)
        } else {
            LayoutInflater.from(activity).inflate(R.layout.channel_series_list_item, parent, false)
        }
        return ViewHolder(view, isCourse)
    }

    override fun getItemCount(): Int {
        return if (channel?.latestMedia?.size ?: 0 > 6)
            return 6
        else channel?.latestMedia?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = channel?.latestMedia?.get(position)?.title
        Picasso.get().load(channel?.latestMedia?.get(position)?.coverAsset?.url).noPlaceholder()
            .resize(608, 912).into(holder.image)
    }

    fun setData(data: Channel) {
        channel = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View, isCourse: Boolean) : RecyclerView.ViewHolder(view) {
        var title: TextView? = null
        var image: ImageView? = null

        init {
            if (isCourse) {
                this.title = view.course_title_tv
                this.image = view.course_image_iv
            } else {
                this.title = view.series_title_tv
                this.image = view.series_image_iv
            }
        }
    }
}