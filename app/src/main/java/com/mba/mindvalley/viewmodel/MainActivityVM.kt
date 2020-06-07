package com.mba.mindvalley.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mba.mindvalley.model.CategoryResponseDataCategory
import com.mba.mindvalley.model.Channel
import com.mba.mindvalley.model.NewEpisodeResponseDataMedia
import com.mba.mindvalley.shared.api.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityVM : ViewModel() {
    val newEpisodeResponseSuccess: MutableLiveData<List<NewEpisodeResponseDataMedia>> by lazy {
        MutableLiveData<List<NewEpisodeResponseDataMedia>>()
    }

    val newEpisodeResponseError: MutableLiveData<Throwable> by lazy {
        MutableLiveData<Throwable>()
    }

    val categoryResponseSuccess: MutableLiveData<List<CategoryResponseDataCategory>> by lazy {
        MutableLiveData<List<CategoryResponseDataCategory>>()
    }

    val categoryResponseError: MutableLiveData<Throwable> by lazy {
        MutableLiveData<Throwable>()
    }

    val channelResponseSuccess: MutableLiveData<List<Channel>> by lazy {
        MutableLiveData<List<Channel>>()
    }

    val channelResponseError: MutableLiveData<Throwable> by lazy {
        MutableLiveData<Throwable>()
    }

    fun getNewEpisodeData() {
        val service = ApiFactory.coreApi
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getNewEpisodesAsync()
            try {
                val response = request.await()
                newEpisodeResponseSuccess.postValue(response.body()?.data?.media)
            } catch (e: Exception) {
                newEpisodeResponseError.postValue(e)
            }
        }
    }

    fun getCategoryData() {
        val service = ApiFactory.coreApi
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getCategoriesAsync()
            try {
                val response = request.await()
                categoryResponseSuccess.postValue(response.body()?.data?.categories)
            } catch (e: Exception) {
                categoryResponseError.postValue(e)
            }
        }
    }

    fun getChannelData() {
        val service = ApiFactory.coreApi
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getChannelsAsync()
            try {
                val response = request.await()
                channelResponseSuccess.postValue(response.body()?.data?.channels)
            } catch (e: Exception) {
                channelResponseError.postValue(e)
            }
        }
    }
}