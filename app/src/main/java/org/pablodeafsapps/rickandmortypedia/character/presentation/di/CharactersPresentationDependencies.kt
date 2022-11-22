package org.pablodeafsapps.rickandmortypedia.character.presentation.di

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import org.pablodeafsapps.rickandmortypedia.MainActivity
import org.pablodeafsapps.rickandmortypedia.MainPresenter
import org.pablodeafsapps.rickandmortypedia.Mvp

@Module
class CharactersPresentationModule(private val mainActivity: MainActivity) {

    @Provides
    fun providesMvpView() : Mvp.View = mainActivity

    @Provides
    fun providesMvpPresenter(mainPresenter: MainPresenter) : Mvp.Presenter = mainPresenter

}
