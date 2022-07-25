package com.demo.daggersample.network

import com.demo.daggersample.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Singleton
    @Provides
    @Named(Constants.RETROFIT1)

    fun provideRetrofit(@Named(Constants.OKHTTP) okhttp:OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okhttp)
            baseUrl(Constants.BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    @Named(Constants.OKHTTP)
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().apply {
           connectTimeout(60,TimeUnit.SECONDS)
            readTimeout(60,TimeUnit.SECONDS)
           writeTimeout(60,TimeUnit.SECONDS)
            addInterceptor(okHttpLoggingInterceptor())
        }.build()
    }

    private fun okHttpLoggingInterceptor():HttpLoggingInterceptor
    {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

}