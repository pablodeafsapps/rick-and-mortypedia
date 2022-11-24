package org.pablodeafsapps.rickandmortypedia.character.data.db

import androidx.room.*

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPage(page: PageEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacterList(list: List<CharacterEntity>)

    @Transaction
    @Query("SELECT * FROM PageEntity")
    suspend fun getPageWithCharacters(): List<PageWithCharacters>

}
