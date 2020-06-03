package com.mba.mindvalley.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
}