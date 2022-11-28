package org.pablodeafsapps.rickandmortypedia.episode.di

import dagger.Module
import dagger.Subcomponent
import org.pablodeafsapps.rickandmortypedia.character.data.di.CharactersDataModule
import org.pablodeafsapps.rickandmortypedia.character.domain.di.CharactersDomainModule
import org.pablodeafsapps.rickandmortypedia.character.presentation.di.CharactersPresentationModule
import org.pablodeafsapps.rickandmortypedia.character.presentation.view.CharactersFragment
import org.pablodeafsapps.rickandmortypedia.episode.presentation.di.EpisodesPresentationModule
import org.pablodeafsapps.rickandmortypedia.episode.presentation.view.EpisodesFragment

interface EpisodesComponentFactoryProvider {
    fun provideEpisodesComponentFactory() : EpisodesComponent.Factory
}

@Module(subcomponents = [ EpisodesComponent::class ])
object EpisodesModule

@Subcomponent(modules = [ EpisodesPresentationModule::class ])
interface EpisodesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            presentationModule: EpisodesPresentationModule
        ): EpisodesComponent
    }

    fun inject(fragment: EpisodesFragment)

}
