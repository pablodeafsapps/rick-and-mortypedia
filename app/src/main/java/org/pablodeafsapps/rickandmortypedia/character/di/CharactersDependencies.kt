package org.pablodeafsapps.rickandmortypedia.character.di

import dagger.Module
import dagger.Subcomponent
import org.pablodeafsapps.rickandmortypedia.character.data.di.CharactersDataModule
import org.pablodeafsapps.rickandmortypedia.character.domain.di.CharactersDomainModule
import org.pablodeafsapps.rickandmortypedia.character.presentation.di.CharactersPresentationModule
import org.pablodeafsapps.rickandmortypedia.character.presentation.view.CharactersFragment

interface CharactersComponentFactoryProvider {
    fun provideCharactersComponentFactory() : CharactersComponent.Factory
}

@Module(subcomponents = [ CharactersComponent::class ])
object CharactersModule

@Subcomponent(modules = [
    CharactersPresentationModule::class, CharactersDomainModule::class, CharactersDataModule::class
])
interface CharactersComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            presentationModule: CharactersPresentationModule
        ): CharactersComponent
    }

    fun inject(fragment: CharactersFragment)

}
