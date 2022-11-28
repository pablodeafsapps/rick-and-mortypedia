package org.pablodeafsapps.rickandmortypedia.episode.domain.usecase

import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes
import javax.inject.Inject

class GetAllEpisodesUc @Inject constructor(
    private val episodeRepository: EpisodesDomainLayerContract.DataLayer.EpisodeRepository
) : EpisodesDomainLayerContract.PresentationLayer.UseCase {

    override suspend fun getAllEpisodes(): Result<Episodes> = episodeRepository.getAllEpisodesList()

}
