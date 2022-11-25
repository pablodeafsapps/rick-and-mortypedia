package org.pablodeafsapps.rickandmortypedia.character.data.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val info: String,
    val results: String
)

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
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

data class OriginEntity(
    @ColumnInfo(name = "origin_name") val name: String,
    @ColumnInfo(name = "origin_url") val url: String
)

data class LocationEntity(
    @ColumnInfo(name = "location_name") val name: String,
    @ColumnInfo(name = "location_url") val url: String
)
