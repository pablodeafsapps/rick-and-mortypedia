package org.pablodeafsapps.rickandmortypedia

import android.app.Application
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponentFactoryProvider
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.common.di.*
import org.pablodeafsapps.rickandmortypedia.episode.di.EpisodesComponent
import org.pablodeafsapps.rickandmortypedia.episode.di.EpisodesComponentFactoryProvider
import org.pablodeafsapps.rickandmortypedia.episode.di.EpisodesComponentProvider
import org.pablodeafsapps.rickandmortypedia.main.di.MainComponent
import org.pablodeafsapps.rickandmortypedia.main.di.MainComponentFactoryProvider

class RickAndMortyApplication : Application(), MainComponentFactoryProvider,
    CharactersComponentFactoryProvider, EpisodesComponentProvider {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory()
            .create(UtilsModule(applicationContext = applicationContext))
    }

    override fun provideMainComponentFactory(): MainComponent.Factory =
        appComponent.mainComponentFactory()

    override fun provideCharactersComponentFactory(): CharactersComponent.Factory =
        appComponent.charactersComponentFactory()

//    override fun provideEpisodesComponentFactory(): EpisodesComponent.Factory =
    //        appComponent.episodesComponentFactory()

    override fun provideEpisodesComponent(): EpisodesComponent =
        appComponent.episodesComponentFactory().create()

}
