package org.pablodeafsapps.rickandmortypedia.common.data.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.pablodeafsapps.rickandmortypedia.character.data.db.*
import org.pablodeafsapps.rickandmortypedia.common.data.db.ReferenceListConverter
import org.pablodeafsapps.rickandmortypedia.episode.data.db.EpisodeEntity
import org.pablodeafsapps.rickandmortypedia.episode.data.db.EpisodesDao

@Database(
    entities = [ TestEntity::class, CharacterEntity::class, EpisodeEntity::class ],
    version = 1
)
@TypeConverters(ReferenceListConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao

    abstract fun charactersDao(): CharactersDao

    abstract fun episodesDao(): EpisodesDao

}
