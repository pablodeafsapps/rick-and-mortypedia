package org.pablodeafsapps.rickandmortypedia.episode.datasource

import org.pablodeafsapps.rickandmortypedia.common.db.ApplicationDatabase
import org.pablodeafsapps.rickandmortypedia.episode.data.api.EpisodesService
import org.pablodeafsapps.rickandmortypedia.episode.data.db.EpisodeEntity
import org.pablodeafsapps.rickandmortypedia.episode.data.model.EpisodesDto
import retrofit2.Retrofit
import javax.inject.Inject

interface EpisodesDataSource {

    interface Remote {

        suspend fun getAllEpisodesListResponse(): Result<EpisodesDto?>

    }

    interface Local {

        suspend fun saveEpisodeList(list: List<EpisodeEntity>)

        suspend fun fetchEpisodeList(): List<EpisodeEntity>

    }

}

class RickAndMortyEpisodeDataSource @Inject constructor(
    private val retrofitInstance: Retrofit,
    private val roomDatabaseInstance: ApplicationDatabase
) : EpisodesDataSource.Remote, EpisodesDataSource.Local {

    override suspend fun getAllEpisodesListResponse(): Result<EpisodesDto?> =
        retrofitInstance.create(EpisodesService::class.java).getAllEpisodesList().runCatching { body() }

    override suspend fun saveEpisodeList(list: List<EpisodeEntity>) {
        // This line simply logs the size of the 'TestEntity' right before adding a new entry
//        println(roomDatabaseInstance.testDao().getAll().size)
//        val testEntity: TestEntity = with(dto) {
//            TestEntity(info = info.count.toString(), results = results.toString())
//        }
//        roomDatabaseInstance.testDao().insertAll(testEntity)
        roomDatabaseInstance.episodesDao().insertAll(*list.toTypedArray())
    }

    override suspend fun fetchEpisodeList(): List<EpisodeEntity> =
        roomDatabaseInstance.episodesDao().getAllEpisodes()

}
