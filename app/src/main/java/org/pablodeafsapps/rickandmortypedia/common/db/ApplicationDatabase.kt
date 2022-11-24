package org.pablodeafsapps.rickandmortypedia.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.pablodeafsapps.rickandmortypedia.character.data.db.*

@Database(
    entities = [ PageEntity::class, CharacterEntity::class ],
    version = 1
)
@TypeConverters(EpisodeReferenceConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

}
