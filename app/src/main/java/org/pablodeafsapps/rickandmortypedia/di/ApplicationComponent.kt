package org.pablodeafsapps.rickandmortypedia.di

import dagger.Component
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersModule

@Component(
    modules = [ CharactersModule::class ]
)
interface ApplicationComponent {

    fun charactersComponentFactory(): CharactersComponent.Factory

}
