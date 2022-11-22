package org.pablodeafsapps.rickandmortypedia.character.di

import dagger.Module
import dagger.Subcomponent
import org.pablodeafsapps.rickandmortypedia.MainActivity
import org.pablodeafsapps.rickandmortypedia.character.presentation.di.CharactersPresentationModule

interface CharactersComponentFactoryProvider {
    fun provideCharactersComponentFactory() : CharactersComponent.Factory
}

@Module(subcomponents = [CharactersComponent::class])
object CharactersModule

@Subcomponent(modules = [ CharactersPresentationModule::class ])
interface CharactersComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(module: CharactersPresentationModule) : CharactersComponent
    }

    fun inject(activity: MainActivity)

}
