package org.pablodeafsapps.rickandmortypedia.common.di

import dagger.Component
import org.pablodeafsapps.rickandmortypedia.character.data.repository.RickAndMortyCharacterRepository
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersModule
import org.pablodeafsapps.rickandmortypedia.character.domain.usecase.GetAllCharactersUc

@Component(
    modules = [ CharactersModule::class, UtilsModule::class ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            utilsModule: UtilsModule
        ): ApplicationComponent
    }

    fun charactersComponentFactory(): CharactersComponent.Factory

}
