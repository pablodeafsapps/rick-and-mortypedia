package org.pablodeafsapps.rickandmortypedia.episode.domain.di

import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.episode.domain.usecase.GetAllEpisodesUc

@Module
class EpisodesDomainModule {

    @Provides
    fun providesGetAllEpisdodesUc(usecase: GetAllEpisodesUc) : @JvmSuppressWildcards EpisodesDomainLayerContract.PresentationLayer.UseCase = usecase

}
