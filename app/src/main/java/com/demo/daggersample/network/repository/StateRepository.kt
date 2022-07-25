package com.demo.daggersample.network.repository

import com.demo.daggersample.BaseResult
import com.demo.daggersample.model.StateData
import com.demo.daggersample.model.StateList
import com.demo.daggersample.network.entity.StateEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface StateRepository {
    suspend fun getstateList(req:String): Flow<BaseResult<StateEntity,Response<List<StateList>>>>
}