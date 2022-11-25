package org.pablodeafsapps.rickandmortypedia

import android.app.Application
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponentFactoryProvider
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.common.di.ApplicationComponent
import org.pablodeafsapps.rickandmortypedia.common.di.DaggerApplicationComponent
import org.pablodeafsapps.rickandmortypedia.common.di.UtilsModule

class RickAndMortyApplication : Application(), CharactersComponentFactoryProvider {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(UtilsModule(applicationContext = applicationContext))
    }

    override fun provideCharactersComponentFactory(): CharactersComponent.Factory =
        appComponent.charactersComponentFactory()

}
