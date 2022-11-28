package org.pablodeafsapps.rickandmortypedia.episode.data.di

import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.episode.data.repository.RickAndMortyEpisodeRepository
import org.pablodeafsapps.rickandmortypedia.episode.datasource.EpisodesDataSource
import org.pablodeafsapps.rickandmortypedia.episode.datasource.RickAndMortyEpisodeDataSource
import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract

@Module
class EpisodesDataModule {

    @Provides
    fun providesEpisodeRepository(
        remoteDataSource: EpisodesDataSource.Remote,
        localDataSource: EpisodesDataSource.Local
    ): EpisodesDomainLayerContract.DataLayer.EpisodeRepository = RickAndMortyEpisodeRepository.apply {
        episodesRemoteDataSource = remoteDataSource
        episodesLocalDataSource = localDataSource
    }

    @Provides
    fun providesEpisodesRemoteDataSource(rickAndMortyDataSource: RickAndMortyEpisodeDataSource) : EpisodesDataSource.Remote =
        rickAndMortyDataSource

    @Provides
    fun providesEpisodesLocalDataSource(rickAndMortyDataSource: RickAndMortyEpisodeDataSource) : EpisodesDataSource.Local =
        rickAndMortyDataSource

}
