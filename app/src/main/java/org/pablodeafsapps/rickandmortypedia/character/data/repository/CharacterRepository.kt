package org.pablodeafsapps.rickandmortypedia.character.data.repository

import org.pablodeafsapps.rickandmortypedia.character.data.datasource.CharactersDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.utils.toCharacters
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Character
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters

object RickAndMortyCharacterRepository: DomainLayerContract.DataLayer.CharacterRepository {

    lateinit var charactersDataSource: CharactersDataSource
//    private val charactersDataSource: CharactersDataSource by lazy { RickAndMortyCharacterDataSource() }

    override suspend fun getAllCharactersList(): Result<Characters> {
//        charactersDataSource.getAllCharactersListResponse().toCharacters()

        val result = charactersDataSource.getAllCharactersListResponse()

//        return if (result.isSuccess) {
//            result.getOrNull()?.toCharacters() ?: Characters(results = emptyList())
//        } else {
//            Characters(results = emptyList())
//        }

        return result.map { dto ->
            if (dto == null) {
                Characters(results = emptyList())
            } else {
                dto.toCharacters()
            }
        }

    }

    override suspend fun getAllCharactersListByPage(page: Int): Characters {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(id: Int): Character {
        TODO("Not yet implemented")
    }

    override suspend fun getMultipleCharactersById(ids: List<Int>): Characters {
        TODO("Not yet implemented")
    }

}
