package com.demo.daggersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import com.demo.daggersample.network.entity.StateEntity
import com.demo.daggersample.viewmodel.StateListProcessState
import com.demo.daggersample.viewmodel.StateListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.demo.daggersample.model.StateList
import kotlinx.coroutines.flow.launchIn
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val stateviewmodel:StateListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val viewmodel = ViewModelProviders
        getList()
        observeStateList()
    }

    private fun getList(){
        stateviewmodel.getStateList("Indiannnnnn")
    }

    fun observeStateList(){
        stateviewmodel.mState
            .flowWithLifecycle(lifecycle,Lifecycle.State.STARTED)
            .onEach { state -> handlechange(state)}
            .launchIn(lifecycleScope)
    }

    private fun handlechange(state:StateListProcessState){
        println("Response State === $state")
        when(state){
            is StateListProcessState.Success ->handlesuccess(state.stateEntity)
            is StateListProcessState.Error ->handleError(state.rawresponse)
        }
    }

    private fun handlesuccess(stateEntity: StateEntity){

        println("State val ${stateEntity.statedata}")

    }

    private fun handleError(stateEntity: Response<List<StateList>>){

        println("State val ${stateEntity.raw().body.toString()}")

    }
}