package org.pablodeafsapps.rickandmortypedia.common.di

import dagger.Component
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersModule
import org.pablodeafsapps.rickandmortypedia.main.di.MainComponent
import org.pablodeafsapps.rickandmortypedia.main.di.MainModule

@Component(
    modules = [MainModule::class, CharactersModule::class, UtilsModule::class]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            utilsModule: UtilsModule
        ): ApplicationComponent
    }

    fun mainComponentFactory(): MainComponent.Factory

    fun charactersComponentFactory(): CharactersComponent.Factory

}
