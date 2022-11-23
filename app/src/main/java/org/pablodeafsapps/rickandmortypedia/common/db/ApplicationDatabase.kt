package org.pablodeafsapps.rickandmortypedia.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.pablodeafsapps.rickandmortypedia.character.data.db.*

@Database(
    entities = [ CharactersEntity::class, InfoEntity::class, CharacterEntity::class, OriginEntity::class, LocationEntity::class ],
    version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

}
