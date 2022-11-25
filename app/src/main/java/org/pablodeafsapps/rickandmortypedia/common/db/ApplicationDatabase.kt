package org.pablodeafsapps.rickandmortypedia.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.pablodeafsapps.rickandmortypedia.character.data.db.*

@Database(
    entities = [ TestEntity::class, CharacterEntity::class ],
    version = 1
)
@TypeConverters(EpisodeReferenceListConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao

    abstract fun charactersDao(): CharactersDao

}
