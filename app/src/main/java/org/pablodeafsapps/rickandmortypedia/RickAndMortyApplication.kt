package org.pablodeafsapps.rickandmortypedia

import android.app.Application
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponentFactoryProvider
import org.pablodeafsapps.rickandmortypedia.common.di.ApplicationComponent
import org.pablodeafsapps.rickandmortypedia.common.di.DaggerApplicationComponent
import org.pablodeafsapps.rickandmortypedia.common.di.UtilsModule
import org.pablodeafsapps.rickandmortypedia.episode.di.EpisodesComponent
import org.pablodeafsapps.rickandmortypedia.episode.di.EpisodesComponentProvider
import org.pablodeafsapps.rickandmortypedia.login.di.LoginComponent
import org.pablodeafsapps.rickandmortypedia.login.di.LoginComponentProvider
import org.pablodeafsapps.rickandmortypedia.main.di.MainComponent
import org.pablodeafsapps.rickandmortypedia.main.di.MainComponentFactoryProvider

class RickAndMortyApplication : Application(), MainComponentFactoryProvider,
    CharactersComponentFactoryProvider, EpisodesComponentProvider, LoginComponentProvider {

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

//    override fun providesEpisodesComponentFactory(): EpisodesComponent.Factory =
    //        appComponent.episodesComponentFactory()

    override fun providesEpisodesComponent(): EpisodesComponent =
        appComponent.episodesComponentFactory().create()

    override fun providesLoginComponent(): LoginComponent =
        appComponent.loginComponentFactory().create()

}
