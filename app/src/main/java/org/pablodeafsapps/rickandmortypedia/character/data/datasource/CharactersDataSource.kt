package org.pablodeafsapps.rickandmortypedia.character.data.datasource

import org.pablodeafsapps.rickandmortypedia.character.data.api.CharactersService
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharactersDto
import org.pablodeafsapps.rickandmortypedia.character.data.utils.getRetrofitInstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CharactersDataSource {

    suspend fun getAllCharactersListResponse(): CharactersDto?

}

class RickAndMortyCharacterDataSource : CharactersDataSource {

    private val retrofitInstance: Retrofit by lazy { getRetrofitInstance(converterFactory = GsonConverterFactory.create()) }

    override suspend fun getAllCharactersListResponse(): CharactersDto? =
        retrofitInstance.create(CharactersService::class.java).getAllCharactersList()

}
