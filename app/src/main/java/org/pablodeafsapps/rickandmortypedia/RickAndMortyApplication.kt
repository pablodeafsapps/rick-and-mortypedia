package org.pablodeafsapps.rickandmortypedia

import android.app.Application
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponentFactoryProvider
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.di.ApplicationComponent
import org.pablodeafsapps.rickandmortypedia.di.DaggerApplicationComponent

class RickAndMortyApplication : Application(), CharactersComponentFactoryProvider {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
    }

    override fun provideCharactersComponentFactory(): CharactersComponent.Factory =
        appComponent.charactersComponentFactory()

}
