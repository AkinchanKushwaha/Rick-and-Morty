package com.example.rickandmorty

import com.example.rickandmorty.models.RickAndMortyData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {
    @GET("character")
    fun getCharacterData(
        @Query("page") pageNumber: Int
    ): Call<RickAndMortyData>
}