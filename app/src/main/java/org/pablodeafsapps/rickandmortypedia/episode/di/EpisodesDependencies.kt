package org.pablodeafsapps.rickandmortypedia.episode.di

import dagger.Module
import dagger.Subcomponent
import org.pablodeafsapps.rickandmortypedia.episode.data.di.EpisodesDataModule
import org.pablodeafsapps.rickandmortypedia.episode.domain.di.EpisodesDomainModule
import org.pablodeafsapps.rickandmortypedia.episode.presentation.view.EpisodesFragment

interface EpisodesComponentProvider {
    fun provideEpisodesComponent() : EpisodesComponent
}

@Module(subcomponents = [ EpisodesComponent::class ])
object EpisodesModule

@Subcomponent(modules = [
//    EpisodesPresentationModule::class, EpisodesDomainModule::class, EpisodesDataModule::class
    EpisodesDomainModule::class, EpisodesDataModule::class
])
interface EpisodesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
//            presentationModule: EpisodesPresentationModule
        ): EpisodesComponent
    }

    fun inject(fragment: EpisodesFragment)

}
