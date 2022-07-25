package com.demo.daggersample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.daggersample.BaseResult
import com.demo.daggersample.model.StateData
import com.demo.daggersample.model.StateList
import com.demo.daggersample.network.entity.StateEntity
import com.demo.daggersample.network.usecase.StateListUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class StateListViewModel @Inject constructor(private val stateListUsecase: StateListUsecase):ViewModel()
{

    val state= MutableStateFlow<StateListProcessState>(StateListProcessState.Init)

    val mState:StateFlow<StateListProcessState> get() =state

    private fun hideLoading(){
        state.value = StateListProcessState.IsLoading(true)
    }
    private fun setLoading(){
        state.value = StateListProcessState.IsLoading(false)
    }

    private fun showToast(msg:String){
        state.value = StateListProcessState.ShowToast(msg)
    }

    fun getStateList(req:String){
        viewModelScope.launch {
            stateListUsecase.execute(req)
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                    showToast("Exception")
                }
                .collect {
                    hideLoading()

                    when(it){
                        is BaseResult.Error -> state.value = StateListProcessState.Error(it.rawresponse)

                        is BaseResult.Success -> state.value = StateListProcessState.Success(it.data)
                    }
                }
        }
    }

    fun getStateList1(req: String){

        viewModelScope.launch {
            stateListUsecase.execute(req)
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                    showToast("Exception")
                }
                .collect {
                    hideLoading()

                    when(it){
                        is BaseResult.Error -> state.value = StateListProcessState.Error(it.rawresponse)

                        is BaseResult.Success -> state.value = StateListProcessState.Success(it.data)
                    }
                }
        }

    }
}

sealed class StateListProcessState{
    object Init:StateListProcessState()
    data class IsLoading(val isloading:Boolean):StateListProcessState()
    data class ShowToast(val msg:String):StateListProcessState()
    data class Success(val stateEntity: StateEntity):StateListProcessState()
    data class Error(val rawresponse: Response<List<StateList>>):StateListProcessState()
}