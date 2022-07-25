package com.demo.daggersample.network.usecase

import com.demo.daggersample.BaseResult
import com.demo.daggersample.model.StateData
import com.demo.daggersample.model.StateList
import com.demo.daggersample.network.entity.StateEntity
import com.demo.daggersample.network.repository.StateRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class StateListUsecase @Inject constructor(private val stateRepository: StateRepository) {
    suspend fun execute(req:String): Flow<BaseResult<StateEntity, Response<List<StateList>>>>{
        return  stateRepository.getstateList(req)
    }
}