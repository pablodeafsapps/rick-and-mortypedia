package org.pablodeafsapps.rickandmortypedia.character.data.datasource

import org.pablodeafsapps.rickandmortypedia.character.data.api.CharactersService
import org.pablodeafsapps.rickandmortypedia.character.data.db.CharacterEntity
import org.pablodeafsapps.rickandmortypedia.character.data.model.CharactersDto
import org.pablodeafsapps.rickandmortypedia.common.db.ApplicationDatabase
import retrofit2.Retrofit
import javax.inject.Inject

interface CharactersDataSource {

    interface Remote {

        suspend fun getAllCharactersListResponse(): Result<CharactersDto?>

    }

    interface Local {

        suspend fun saveCharacterList(list: List<CharacterEntity>)

        suspend fun fetchCharacterList(): List<CharacterEntity>

    }

}

class RickAndMortyCharacterDataSource @Inject constructor(
    private val retrofitInstance: Retrofit,
    private val roomDatabaseInstance: ApplicationDatabase
) : CharactersDataSource.Remote, CharactersDataSource.Local {

    override suspend fun getAllCharactersListResponse(): Result<CharactersDto?> =
        retrofitInstance.create(CharactersService::class.java).getAllCharactersList().runCatching { body() }

    override suspend fun saveCharacterList(list: List<CharacterEntity>) {
        // This line simply logs the size of the 'TestEntity' right before adding a new entry
//        println(roomDatabaseInstance.testDao().getAll().size)
//        val testEntity: TestEntity = with(dto) {
//            TestEntity(info = info.count.toString(), results = results.toString())
//        }
//        roomDatabaseInstance.testDao().insertAll(testEntity)
        roomDatabaseInstance.charactersDao().insertAll(*list.toTypedArray())
    }

    override suspend fun fetchCharacterList(): List<CharacterEntity> =
        roomDatabaseInstance.charactersDao().getAllCharacters()

}
