package org.pablodeafsapps.rickandmortypedia.data.api

import org.pablodeafsapps.rickandmortypedia.data.model.CharactersDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET("character")
    suspend fun getAllCharactersList(@Query("page") page: Int = 1): CharactersDto?

}
