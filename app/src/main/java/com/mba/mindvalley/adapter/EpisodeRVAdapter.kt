package com.mba.mindvalley.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mba.mindvalley.R
import com.mba.mindvalley.model.NewEpisodeResponseDataMedia
import kotlinx.android.synthetic.main.new_episode_list_item.view.*

class EpisodeRVAdapter(private var activity: Activity) :
    RecyclerView.Adapter<EpisodeRVAdapter.ViewHolder>() {

    private var episodes: MutableList<NewEpisodeResponseDataMedia>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.new_episode_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return episodes?.size ?: return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = episodes?.get(position)?.title
        holder.subTitle?.text = episodes?.get(position)?.channel?.title

        holder.image?.let {
            Glide
                .with(activity)
                .load(episodes?.get(position)?.coverAsset?.url)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(it)
        }
    }

    fun setData(data: List<NewEpisodeResponseDataMedia>) {
        episodes = data as MutableList<NewEpisodeResponseDataMedia>
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView? = null
        var subTitle: TextView? = null
        var image: ImageView? = null

        init {
            this.title = view.series_title
            this.subTitle = view.episode_sub_title
            this.image = view.series_image
        }
    }
}