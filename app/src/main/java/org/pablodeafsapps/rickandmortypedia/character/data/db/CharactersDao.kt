package org.pablodeafsapps.rickandmortypedia.character.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharactersDao {

    @Query("SELECT * FROM character_table")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg characters: CharacterEntity)

    @Delete
    suspend fun delete(characters: CharacterEntity)

}

@Dao
interface TestDao {

    @Query("SELECT * FROM TestEntity")
    suspend fun getAll(): List<TestEntity>

    @Insert
    suspend fun insertAll(vararg test: TestEntity)

    @Delete
    suspend fun delete(test: TestEntity)

}
