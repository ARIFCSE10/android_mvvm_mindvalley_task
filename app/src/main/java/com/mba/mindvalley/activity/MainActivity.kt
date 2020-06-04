package com.mba.mindvalley.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mba.mindvalley.R
import com.mba.mindvalley.adapter.CategoryAdapter
import com.mba.mindvalley.adapter.EpisodeRVAdapter
import com.mba.mindvalley.viewmodel.MainActivityVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityVM: MainActivityVM
    private var episodeRVAdapter: EpisodeRVAdapter? = null
    private var categoryAdapter: CategoryAdapter? = null

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

        categoryAdapter = CategoryAdapter(this)
        category_gv.adapter = categoryAdapter
    }

    private fun setupViewModel() {
        mainActivityVM = ViewModelProvider(this).get(MainActivityVM::class.java)

//        Episodes
        mainActivityVM.newEpisodeResponseSuccess.observe(this, Observer { response ->
            episodeRVAdapter?.setData(response)
        })

        mainActivityVM.newEpisodeResponseError.observe(this, Observer { error ->
            Toast.makeText(this, "Episode Error : ${error.localizedMessage}", Toast.LENGTH_LONG)
                .show()
        })

        mainActivityVM.getNewEpisodeData()

//        Categories
        mainActivityVM.categoryResponseSuccess.observe(this, Observer { response ->
            categoryAdapter?.setData(response)
        })

        mainActivityVM.categoryResponseError.observe(this, Observer { error ->
            Toast.makeText(this, "Category Error : ${error.localizedMessage}", Toast.LENGTH_LONG)
                .show()
        })

        mainActivityVM.getCategoryData()
    }
}
