package org.pablodeafsapps.rickandmortypedia.character.di

import dagger.Module
import dagger.Subcomponent
import org.pablodeafsapps.rickandmortypedia.MainActivity
import org.pablodeafsapps.rickandmortypedia.character.data.di.CharactersDataModule
import org.pablodeafsapps.rickandmortypedia.character.domain.di.CharactersDomainModule
import org.pablodeafsapps.rickandmortypedia.character.presentation.di.CharactersPresentationModule

interface CharactersComponentFactoryProvider {
    fun provideCharactersComponentFactory() : CharactersComponent.Factory
}

@Module(subcomponents = [CharactersComponent::class])
object CharactersModule

@Subcomponent(modules = [
    CharactersPresentationModule::class, CharactersDomainModule::class, CharactersDataModule::class
])
interface CharactersComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            presentationModule: CharactersPresentationModule,
            dataModule: CharactersDataModule
        ) : CharactersComponent
    }

    fun inject(activity: MainActivity)

}
