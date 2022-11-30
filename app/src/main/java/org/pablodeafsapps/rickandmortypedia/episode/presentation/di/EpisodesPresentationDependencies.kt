package org.pablodeafsapps.rickandmortypedia.episode.presentation.di

import androidx.savedstate.SavedStateRegistryOwner
import dagger.Module
import dagger.Provides

@Module
//class EpisodesPresentationModule(private val episodesView: EpisodesContract.View) {
class EpisodesPresentationModule(private val owner: SavedStateRegistryOwner) {

    @Provides
    fun providesSavedStateRegistryOwner() : SavedStateRegistryOwner = owner

//    @Provides
//    fun providesEpisodesView() : EpisodesContract.View = episodesView

//    @Provides
//    fun providesEpisodesPresenter(episodesPresenter: EpisodesPresenter) : EpisodesContract.Presenter = episodesPresenter

}
