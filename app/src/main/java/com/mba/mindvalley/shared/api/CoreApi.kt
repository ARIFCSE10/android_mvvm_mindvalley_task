package com.mba.mindvalley.shared.api

import com.mba.mindvalley.model.CategoryResponse
import com.mba.mindvalley.model.ChannelResponse
import com.mba.mindvalley.model.NewEpisodeResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CoreApi {
    @GET("/raw/z5AExTtw")
    fun getNewEpisodesAsync(): Deferred<Response<NewEpisodeResponse>>

    @GET("/raw/A0CgArX3")
    fun getCategoriesAsync(): Deferred<Response<CategoryResponse>>

    @GET("/raw/Xt12uVhM")
    fun getChannelsAsync(): Deferred<Response<ChannelResponse>>
}