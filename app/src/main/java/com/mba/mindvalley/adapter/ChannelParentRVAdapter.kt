package com.mba.mindvalley.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mba.mindvalley.R
import com.mba.mindvalley.model.Channel
import kotlinx.android.synthetic.main.channel_parent_list_item.view.*

class ChannelParentRVAdapter(private var activity: Activity) :
    RecyclerView.Adapter<ChannelParentRVAdapter.ViewHolder>() {

    private var channels: MutableList<Channel>? = null
    private var childRVAdapters: MutableList<ChannelChildRVAdapter>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.channel_parent_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return channels?.size ?: return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.header?.text = channels?.get(position)?.title
        holder.subHeader?.text = getSubHeaderText(position)

        holder.icon?.let { imageView ->
            if (!channels?.get(position)?.iconAsset?.thumbnailUrl.isNullOrEmpty()) {
                loadCachedImage(
                    url = channels?.get(position)?.iconAsset?.thumbnailUrl,
                    imageView = imageView,
                    position = position
                )
            } else if (!channels?.get(position)?.iconAsset?.url.isNullOrEmpty()) {
                loadCachedImage(
                    url = channels?.get(position)?.iconAsset?.url,
                    imageView = imageView,
                    position = position
                )
            } else {
                Glide.with(activity).load(R.drawable.placeholder_icon)
                    .into(imageView)
            }
        }

        holder.childRV?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        channels?.get(position)?.let { childRVAdapters?.get(position)?.setData(it) }
        holder.childRV?.adapter = childRVAdapters?.get(position)

    }

    private fun loadCachedImage(url: String?, imageView: ImageView, position: Int) {
        Glide.with(activity).load(url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.placeholder_icon)
            .into(imageView)
    }

    fun setData(data: List<Channel>) {
        channels = data as MutableList<Channel>
        val size = channels?.size ?: 0
        childRVAdapters = MutableList(size) { position ->
            return@MutableList ChannelChildRVAdapter(activity, isCourse(position))
        }
        notifyDataSetChanged()
    }

    private fun getSubHeaderText(position: Int): String {
        val isCurrentChannelCourse = isCourse(position)
        if (isCurrentChannelCourse) return "${channels?.get(position)?.mediaCount} episodes"
        return "${channels?.get(position)?.mediaCount} series"
    }

    private fun isCourse(position: Int): Boolean {
        channels?.get(position)?.series?.isEmpty().let {
            return it ?: true
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var header: TextView? = null
        var subHeader: TextView? = null
        var icon: ImageView? = null
        var childRV: RecyclerView? = null

        init {
            header = view.channel_header_tv
            subHeader = view.channel_sub_header_tv
            icon = view.channel_icon_iv
            childRV = view.channels_child_rv
        }
    }
}