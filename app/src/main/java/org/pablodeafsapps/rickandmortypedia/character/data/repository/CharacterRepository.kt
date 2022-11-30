package org.pablodeafsapps.rickandmortypedia.character.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.pablodeafsapps.rickandmortypedia.character.data.datasource.CharactersDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.utils.toCharacters
import org.pablodeafsapps.rickandmortypedia.character.data.utils.toCharactersEntity
import org.pablodeafsapps.rickandmortypedia.character.domain.CharactersDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Character
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters

object RickAndMortyCharacterRepository: CharactersDomainLayerContract.DataLayer.CharacterRepository {

    private var nextPage: Int = 1
    lateinit var charactersRemoteDataSource: CharactersDataSource.Remote
    lateinit var charactersLocalDataSource: CharactersDataSource.Local

    override suspend fun getAllCharactersList(): Result<Characters> =
        try {
            charactersRemoteDataSource.getAllCharactersListResponse().map { dto ->
                dto?.toCharacters()?.also {
                    withContext(Dispatchers.IO) {
                        charactersLocalDataSource.saveCharacterList(list = dto.toCharactersEntity())
                    }
                    nextPage++
                } ?: charactersLocalDataSource.fetchCharacterList().toCharacters()
            }
        } catch (e: Exception) {
            Result.success(charactersLocalDataSource.fetchCharacterList().toCharacters())
        }

    override suspend fun getCharactersNextPage(): Result<Characters> =
        try {
            charactersRemoteDataSource.getCharactersNextPage(page = nextPage).map { dto ->
                dto?.toCharacters()?.also {
                    withContext(Dispatchers.IO) {
                        charactersLocalDataSource.saveCharacterList(list = dto.toCharactersEntity())
                    }
                    nextPage++
                } ?: charactersLocalDataSource.fetchCharacterList().toCharacters()
            }
        } catch (e: Exception) {
            Result.success(charactersLocalDataSource.fetchCharacterList().toCharacters())
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
