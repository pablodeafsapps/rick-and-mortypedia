package org.pablodeafsapps.rickandmortypedia.episode

import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episode
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes

interface EpisodesDomainLayerContract {

    interface PresentationLayer {

        interface UseCase {

            suspend fun getAllEpisodes(): Result<Episodes>

        }

    }

    interface DataLayer {

        interface EpisodeRepository {

            suspend fun getAllEpisodesList() : Result<Episodes>

            suspend fun getAllEpisodesListByPage(page: Int) : Episodes

            suspend fun getEpisodeById(id: Int) : Episode

            suspend fun getMultipleEpisodesById(ids: List<Int>) : Episodes

        }

    }

}
