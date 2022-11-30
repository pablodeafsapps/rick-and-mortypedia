package org.pablodeafsapps.rickandmortypedia.main.presentation.di

import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.character.presentation.CharactersContract
import org.pablodeafsapps.rickandmortypedia.character.presentation.presenter.CharactersPresenter
import org.pablodeafsapps.rickandmortypedia.main.MainContract
import org.pablodeafsapps.rickandmortypedia.main.presentation.presenter.MainPresenter

@Module
class MainPresentationModule(private val mainView: MainContract.View) {

    @Provides
    fun providesMainView() : MainContract.View = mainView

    @Provides
    fun providesMainPresenter(mainPresenter: MainPresenter) : MainContract.Presenter = mainPresenter

}
