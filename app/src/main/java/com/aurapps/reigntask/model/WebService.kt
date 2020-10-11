package com.aurapps.reigntask.model

import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("search_by_date?query=android")
    fun getStories(): Call<WebResponse?>?

}