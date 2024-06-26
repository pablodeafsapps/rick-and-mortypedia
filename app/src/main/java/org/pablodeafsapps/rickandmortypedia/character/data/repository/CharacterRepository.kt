package org.pablodeafsapps.rickandmortypedia.character.data.repository

import kotlinx.coroutines.*
import org.pablodeafsapps.rickandmortypedia.character.data.datasource.CharactersDataSource
import org.pablodeafsapps.rickandmortypedia.character.data.db.CharacterEntity
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharactersDto
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
                        charactersLocalDataSource.saveCharacterList(list = dto.toCharactersEntity(page = nextPage))
                        nextPage++
                    }
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
                        charactersLocalDataSource.saveCharacterList(list = dto.toCharactersEntity(page = nextPage))
                        nextPage++
                    }
                } ?: run {
                    withContext(Dispatchers.IO) {
                        charactersLocalDataSource.fetchCharactersNextPage(page = nextPage).toCharacters()
                            .also { if (it.results.isNotEmpty()) { nextPage++ } }
                    }
                }
            }
        } catch (e: Exception) {
            Result.success(
                withContext(Dispatchers.IO) {
                    charactersLocalDataSource.fetchCharactersNextPage(page = nextPage).toCharacters()
                        .also { if (it.results.isNotEmpty()) { nextPage++ } }
                }
            )
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

data class MyResult(val name: String?, val status: String)
