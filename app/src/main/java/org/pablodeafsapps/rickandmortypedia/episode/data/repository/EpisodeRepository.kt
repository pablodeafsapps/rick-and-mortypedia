package org.pablodeafsapps.rickandmortypedia.episode.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.pablodeafsapps.rickandmortypedia.episode.datasource.EpisodesDataSource
import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episode
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes
import org.pablodeafsapps.rickandmortypedia.episode.utils.toEpisodes
import org.pablodeafsapps.rickandmortypedia.episode.utils.toEpisodesEntity

object RickAndMortyEpisodeRepository: EpisodesDomainLayerContract.DataLayer.EpisodeRepository {

    lateinit var episodesRemoteDataSource: EpisodesDataSource.Remote
    lateinit var episodesLocalDataSource: EpisodesDataSource.Local

    override suspend fun getAllEpisodesList(): Result<Episodes> =
        try {
            episodesRemoteDataSource.getAllEpisodesListResponse().map { dto ->
                dto?.toEpisodes()?.also {
                    withContext(Dispatchers.IO) {
                        episodesLocalDataSource.saveEpisodeList(list = dto.toEpisodesEntity())
                    }
                } ?: episodesLocalDataSource.fetchEpisodeList().toEpisodes()
            }
        } catch (e: Exception) {
            Result.success(episodesLocalDataSource.fetchEpisodeList().toEpisodes())
        }

    override suspend fun getAllEpisodesListByPage(page: Int): Episodes {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisodeById(id: Int): Episode {
        TODO("Not yet implemented")
    }

    override suspend fun getMultipleEpisodesById(ids: List<Int>): Episodes {
        TODO("Not yet implemented")
    }

}
