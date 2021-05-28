package com.example.rickandmorty

import com.example.rickandmorty.models.RickAndMortyData
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyService {
    @GET("character")
    fun getCharacterData(): Call<RickAndMortyData>
}