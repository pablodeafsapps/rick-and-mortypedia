package org.pablodeafsapps.rickandmortypedia.character.data.datasource

import org.pablodeafsapps.rickandmortypedia.character.data.api.CharactersService
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharactersDto
import retrofit2.Retrofit
import javax.inject.Inject

interface CharactersDataSource {

    interface Remote {

        suspend fun getAllCharactersListResponse(): Result<CharactersDto?>

    }

    interface Local {

    }

}

class RickAndMortyCharacterDataSource @Inject constructor(
    private val retrofitInstance: Retrofit
) : CharactersDataSource.Remote, CharactersDataSource.Local {

//    private val retrofitInstance: Retrofit by lazy { getRetrofitInstance(converterFactory = GsonConverterFactory.create()) }

    override suspend fun getAllCharactersListResponse(): Result<CharactersDto?> =
        retrofitInstance.create(CharactersService::class.java).getAllCharactersList().runCatching { body() }

}
