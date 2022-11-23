package org.pablodeafsapps.rickandmortypedia.character.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val info: String,
    val results: String
)

@Entity
data class CharactersEntity(
    @PrimaryKey val uid: Int,
    val info: InfoEntity,
    val results: List<CharacterEntity>
)

@Entity
data class InfoEntity(
    @PrimaryKey val uid: Int,
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginEntity,
    val location: LocationEntity,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

@Entity
data class OriginEntity(
    @PrimaryKey val uid: Int,
    val name: String,
    val url: String
)

@Entity
data class LocationEntity(
    @PrimaryKey val uid: Int,
    val name: String,
    val url: String
)
