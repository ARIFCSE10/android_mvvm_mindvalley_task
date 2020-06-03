package com.mba.mindvalley.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mba.mindvalley.R
import com.mba.mindvalley.adapter.EpisodeRVAdapter
import com.mba.mindvalley.viewmodel.MainActivityVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityVM: MainActivityVM
    var episodeRVAdapter: EpisodeRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupRecycleView()
    }

    private fun setupRecycleView() {
        episodeRVAdapter = EpisodeRVAdapter(this)
        new_episode_rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        new_episode_rv.adapter = episodeRVAdapter
    }

    private fun setupViewModel() {
        mainActivityVM = ViewModelProvider(this).get(MainActivityVM::class.java)

        mainActivityVM.newEpisodeResponseSuccess.observe(this, Observer { response ->
            episodeRVAdapter?.setData(response)
        })
        mainActivityVM.getNewEpisodeData()
    }
}
