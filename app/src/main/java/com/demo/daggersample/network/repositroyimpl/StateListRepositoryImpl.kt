package com.demo.daggersample.network.repositroyimpl

import com.demo.daggersample.BaseResult
import com.demo.daggersample.Constants
import com.demo.daggersample.model.StateList
import com.demo.daggersample.network.entity.StateEntity
import com.demo.daggersample.network.remote.api.Stateapi
import com.demo.daggersample.network.repository.StateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class StateListRepositoryImpl @Inject constructor(private  val stateapi:Stateapi):StateRepository {
    override suspend fun getstateList(req: String): Flow<BaseResult<StateEntity, Response<List<StateList>>>> {
       return  flow {

           try{
               val response = stateapi.getStateList(Constants.URL1,req)
               println("State Response success == ${response.isSuccessful}")
               println("State Response success == ${response.body()}")

               if(response.isSuccessful){
                   val body = response.body()!!
                  println("State Response == ${body.size}")
                   val stateentiy = StateEntity(body)
                  emit(BaseResult.Success(stateentiy))
               }
               else{
                   val err: Response<List<StateList>> = response
                  emit(BaseResult.Error(err))
               }

           }
           catch (e:Exception){
               println("State Response Exception == ${e.toString()}")
           }
       }
    }
//    override suspend fun getstateList(req:String): Flow<BaseResult<StateEntity, Response<List<StateList>>>> {
//        return flow{
//
//          try {
//              val response = stateapi.getStateList(/*Constants.URL1,*/ req)
//              println("State Response success == ${response.isSuccessful}")
//
//              if (response.isSuccessful) {
//                  val body = response.body()!!
//                  println("State Response == $body")
//                  val stateentiy = StateEntity(body)
//                  emit(BaseResult.Success(stateentiy))
//              } else {
//                  val err: Response<StateList> = response
//                  emit(BaseResult.Error(err))
//              }
//          }
//          catch (e:Exception){
//              println("State Response Exception == ${e.message}")
//          }
//        }
//    }



}