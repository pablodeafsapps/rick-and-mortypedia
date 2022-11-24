package org.pablodeafsapps.rickandmortypedia.character.data.datasource

import org.pablodeafsapps.rickandmortypedia.character.data.api.CharactersService
import org.pablodeafsapps.rickandmortypedia.character.data.db.*
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharactersDto
import org.pablodeafsapps.rickandmortypedia.character.data.utils.toEntity
import org.pablodeafsapps.rickandmortypedia.common.db.ApplicationDatabase
import retrofit2.Retrofit
import javax.inject.Inject

interface CharactersDataSource {

    interface Remote {

        suspend fun getAllCharactersListResponse(): Result<CharactersDto?>

    }

    interface Local {

        suspend fun saveData(dto: CharactersDto)

    }

}

class RickAndMortyCharacterDataSource @Inject constructor(
    private val retrofitInstance: Retrofit,
    private val roomDatabaseInstance: ApplicationDatabase
) : CharactersDataSource.Remote, CharactersDataSource.Local {

    override suspend fun getAllCharactersListResponse(): Result<CharactersDto?> =
        retrofitInstance.create(CharactersService::class.java).getAllCharactersList().runCatching { body() }

    override suspend fun saveData(dto: CharactersDto) {
        with(dto) {
            // TODO: get the current page out of 'Info' from 'CharactersDto'
            val pageEntity: PageEntity = PageEntity(
                currentPage = 1, previousPage = info.prev, nextPage = info.next
            )
            val characterEntityList: List<CharacterEntity> = results.toEntity()

            roomDatabaseInstance.charactersDao().insertPage(page = pageEntity)
            roomDatabaseInstance.charactersDao().insertCharacterList(list = characterEntityList)
        }
        // Check that some data was successfully inserted
        println(roomDatabaseInstance.charactersDao().getPageWithCharacters())
    }

}
