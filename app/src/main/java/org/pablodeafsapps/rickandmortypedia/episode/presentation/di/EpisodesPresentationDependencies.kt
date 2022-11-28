package org.pablodeafsapps.rickandmortypedia.episode.presentation.di

import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.character.presentation.CharactersContract
import org.pablodeafsapps.rickandmortypedia.character.presentation.presenter.CharactersPresenter
import org.pablodeafsapps.rickandmortypedia.episode.presentation.EpisodesContract
import org.pablodeafsapps.rickandmortypedia.episode.presentation.presenter.EpisodesPresenter

@Module
class EpisodesPresentationModule(private val episodesView: EpisodesContract.View) {

    @Provides
    fun providesEpisodesView() : EpisodesContract.View = episodesView

    @Provides
    fun providesEpisodesPresenter(episodesPresenter: EpisodesPresenter) : EpisodesContract.Presenter = episodesPresenter

}
