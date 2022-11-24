package org.pablodeafsapps.rickandmortypedia.character.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.pablodeafsapps.rickandmortypedia.character.data.datasource.CharactersDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharacterDto
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharactersDto
import org.pablodeafsapps.rickandmortypedia.character.data.utils.toCharacters
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Character
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters

object RickAndMortyCharacterRepository: DomainLayerContract.DataLayer.CharacterRepository {

    lateinit var charactersRemoteDataSource: CharactersDataSource.Remote
    lateinit var charactersLocalDataSource: CharactersDataSource.Local

    override suspend fun getAllCharactersList(): Result<Characters> {

        val result: Result<CharactersDto?> = charactersRemoteDataSource.getAllCharactersListResponse()

//        return if (result.isSuccess) {
//            result.getOrNull()?.toCharacters() ?: Characters(results = emptyList())
//        } else {
//            Characters(results = emptyList())
//        }

        return result.map { dto ->
            if (dto == null) {
                Characters(results = emptyList())
            } else {
                withContext(Dispatchers.IO) {
                    charactersLocalDataSource.saveCharacterList(dto)
                }
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
