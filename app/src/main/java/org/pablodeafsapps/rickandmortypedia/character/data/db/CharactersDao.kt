package org.pablodeafsapps.rickandmortypedia.character.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//@Dao
//interface CharactersDao {
//
//    @Query("SELECT * FROM CharactersEntity")
//    fun getAll(): List<CharactersEntity>
//
//    @Insert
//    fun insertAll(vararg characters: CharactersEntity)
//
//    @Delete
//    fun delete(characters: CharactersEntity)
//
//}

@Dao
interface TestDao {

    @Query("SELECT * FROM TestEntity")
    fun getAll(): List<TestEntity>

    @Insert
    fun insertAll(vararg test: TestEntity)

    @Delete
    fun delete(test: TestEntity)

}
