package org.pablodeafsapps.rickandmortypedia.character.data.api

import org.pablodeafsapps.rickandmortypedia.character.data.model.CharactersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET("character")
    suspend fun getAllCharactersList(@Query("page") page: Int = 1): Response<CharactersDto?>

}
