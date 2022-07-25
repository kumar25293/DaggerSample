package com.demo.daggersample.network.remote.api

import com.demo.daggersample.model.StateData
import com.demo.daggersample.model.StateList
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface Stateapi {
//    @GET("/search")
@GET
    suspend fun getStateList(@Url url:String, @Query("country") country: String) : Response<List<StateList>>
}