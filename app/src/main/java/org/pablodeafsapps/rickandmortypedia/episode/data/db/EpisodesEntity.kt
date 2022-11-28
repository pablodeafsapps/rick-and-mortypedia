package org.pablodeafsapps.rickandmortypedia.episode.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_table")
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    @ColumnInfo(name = "air_date") val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
