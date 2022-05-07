package com.wojciechkula.catchacat.di

import com.wojciechkula.catchacat.data.api.CatFactsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {
    private const val BASE_URL = "https://cat-fact.herokuapp.com/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    @Singleton
    @Provides
    fun provideCatFactsApi(retrofit: Retrofit): CatFactsApi = retrofit.create(CatFactsApi::class.java)

}