package org.pablodeafsapps.rickandmortypedia.di

import dagger.Component
import org.pablodeafsapps.rickandmortypedia.character.data.repository.RickAndMortyCharacterRepository
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersModule
import org.pablodeafsapps.rickandmortypedia.character.domain.usecase.GetAllCharactersUc

@Component(
    modules = [ CharactersModule::class ]
)
interface ApplicationComponent {

    fun charactersComponentFactory(): CharactersComponent.Factory

}
