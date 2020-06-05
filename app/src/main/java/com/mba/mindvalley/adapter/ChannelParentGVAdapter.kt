package com.mba.mindvalley.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.mba.mindvalley.R
import com.mba.mindvalley.model.Channel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.channel_parent_list_item.view.*

class ChannelParentGVAdapter(private var activity: Activity) : BaseAdapter() {
    private var channels: MutableList<Channel>? = null

    override fun getCount(): Int {
        return channels?.size ?: return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val channelListItem: View
        if (convertView == null) {
            channelListItem =
                activity.layoutInflater.inflate(R.layout.channel_parent_list_item, parent, false)
            channelListItem.channel_header_tv.text = channels?.get(position)?.title
            channelListItem.channel_sub_header_tv.text =
                "${channels?.get(position)?.mediaCount} episodes"

            if (!channels?.get(position)?.iconAsset?.thumbnailUrl.isNullOrEmpty()) {
                Picasso.get().load(channels?.get(position)?.iconAsset?.thumbnailUrl).noPlaceholder()
                    .resize(50, 50)
                    .into(channelListItem.channel_icon_iv)
            } else if (!channels?.get(position)?.iconAsset?.url.isNullOrEmpty()) {
                Picasso.get().load(channels?.get(position)?.iconAsset?.url).noPlaceholder()
                    .resize(50, 50)
                    .into(channelListItem.channel_icon_iv)
            }
        } else {
            channelListItem = convertView
        }
        return channelListItem
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    fun setData(data: List<Channel>) {
        channels = data as MutableList<Channel>
        notifyDataSetChanged()
    }
}