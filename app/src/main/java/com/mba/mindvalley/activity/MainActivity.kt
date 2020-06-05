package com.mba.mindvalley.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mba.mindvalley.R
import com.mba.mindvalley.adapter.CategoryAdapter
import com.mba.mindvalley.adapter.ChannelParentRVAdapter
import com.mba.mindvalley.adapter.EpisodeRVAdapter
import com.mba.mindvalley.viewmodel.MainActivityVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityVM: MainActivityVM
    private var episodeRVAdapter: EpisodeRVAdapter? = null
    private var categoryAdapter: CategoryAdapter? = null
    private var channelParentAdapter: ChannelParentRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupRecycleView()
        initPullToRefresh()
        loadPageData()
    }

    private fun initPullToRefresh() {
        pull_to_refresh_layout.setOnRefreshListener {
            loadPageData()
        }
    }

    private fun loadPageData() {
        mainActivityVM.getChannelData()
        mainActivityVM.getNewEpisodeData()
        mainActivityVM.getCategoryData()
    }

    private fun setupRecycleView() {
        episodeRVAdapter = EpisodeRVAdapter(this)
        new_episode_rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        new_episode_rv.adapter = episodeRVAdapter

        categoryAdapter = CategoryAdapter(this)
        category_gv.adapter = categoryAdapter


        channelParentAdapter = ChannelParentRVAdapter(this)
        channels_rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        channels_rv.adapter = channelParentAdapter
    }

    private fun setupViewModel() {
        mainActivityVM = ViewModelProvider(this).get(MainActivityVM::class.java)

//        Episodes
        mainActivityVM.newEpisodeResponseSuccess.observe(this, Observer { response ->
            episodeRVAdapter?.setData(response)
            pull_to_refresh_layout.isRefreshing = false
        })

        mainActivityVM.newEpisodeResponseError.observe(this, Observer { error ->
            Toast.makeText(this, "Episode Error : ${error.localizedMessage}", Toast.LENGTH_LONG)
                .show()
            pull_to_refresh_layout.isRefreshing = false
        })

//        Categories
        mainActivityVM.categoryResponseSuccess.observe(this, Observer { response ->
            categoryAdapter?.setData(response)
            pull_to_refresh_layout.isRefreshing = false
        })

        mainActivityVM.categoryResponseError.observe(this, Observer { error ->
            Toast.makeText(this, "Category Error : ${error.localizedMessage}", Toast.LENGTH_LONG)
                .show()
            pull_to_refresh_layout.isRefreshing = false
        })

//        Channel
        mainActivityVM.channelResponseSuccess.observe(this, Observer { response ->
            channelParentAdapter?.setData(response)
            pull_to_refresh_layout.isRefreshing = false
        })

        mainActivityVM.categoryResponseError.observe(this, Observer { error ->
            Toast.makeText(this, "Channel Error : ${error.localizedMessage}", Toast.LENGTH_LONG)
                .show()
            pull_to_refresh_layout.isRefreshing = false
        })
    }
}
