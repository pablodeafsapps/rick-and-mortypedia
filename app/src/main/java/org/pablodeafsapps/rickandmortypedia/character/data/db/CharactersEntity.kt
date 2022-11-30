package org.pablodeafsapps.rickandmortypedia.character.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.pablodeafsapps.rickandmortypedia.common.db.LocationEntity
import org.pablodeafsapps.rickandmortypedia.common.db.OriginEntity

@Entity
data class TestEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val info: String,
    val results: String
)

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val page: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @Embedded val origin: OriginEntity,
    @Embedded val location: LocationEntity,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)
