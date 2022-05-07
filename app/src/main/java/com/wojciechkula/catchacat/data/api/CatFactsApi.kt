package com.wojciechkula.catchacat.data.api

import com.wojciechkula.catchacat.data.entity.FactEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactsApi {

    //    https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=30
    @GET("facts/random")
    suspend fun getRandomFacts(
        @Query("animal_type")
        animalType: String = "cat",
        @Query("amount")
        amount: Int = 30
    ): Response<List<FactEntity>>
}