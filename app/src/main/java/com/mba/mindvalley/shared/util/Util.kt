package com.mba.mindvalley.shared.util

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

object Util {
    fun loadImageFromNetworkOrCache(imageView: ImageView?, url: String?, placeholder: Int) {
        Picasso.get()
            .load(url)
            .placeholder(placeholder)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(imageView, object : Callback {
                override fun onSuccess() {
//                    Toast.makeText(context, "Loaded From Cache", Toast.LENGTH_LONG).show()
                }

                override fun onError(e: Exception?) {
                    Picasso.get()
                        .load(url)
                        .placeholder(placeholder)
                        .into(imageView)
                }
            })
    }
}