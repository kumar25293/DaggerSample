package com.demo.daggersample.module

import com.demo.daggersample.Constants
import com.demo.daggersample.network.NetWorkModule
import com.demo.daggersample.network.remote.api.Stateapi
import com.demo.daggersample.network.repository.StateRepository
import com.demo.daggersample.network.repositroyimpl.StateListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module(includes = [NetWorkModule::class])
@InstallIn(SingletonComponent::class)

class StateModule {

    @Singleton
    @Provides
    fun provideStateAPi(@Named(Constants.RETROFIT1) retrofit: Retrofit):Stateapi{
        return retrofit.create(Stateapi::class.java)
    }

    @Singleton
    @Provides
    fun provideStateRepository( stateapi: Stateapi):StateRepository{
        return StateListRepositoryImpl(stateapi)
    }
}