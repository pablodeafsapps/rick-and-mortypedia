package org.pablodeafsapps.rickandmortypedia.episode.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EpisodesDao {

    @Query("SELECT * FROM episode_table")
    suspend fun getAllEpisodes(): List<EpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg episodes: EpisodeEntity)

    @Delete
    suspend fun delete(episodes: EpisodeEntity)

}
