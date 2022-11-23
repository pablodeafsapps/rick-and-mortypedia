package org.pablodeafsapps.rickandmortypedia.character.presentation.di

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import org.pablodeafsapps.rickandmortypedia.MainActivity
import org.pablodeafsapps.rickandmortypedia.MainPresenter
import org.pablodeafsapps.rickandmortypedia.Mvp
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import org.pablodeafsapps.rickandmortypedia.character.domain.usecase.GetAllCharactersUc
import javax.inject.Named

@Module
class CharactersPresentationModule(private val mvpView: Mvp.View) {

    @Provides
    fun providesMvpView() : Mvp.View = mvpView

    @Provides
    fun providesMvpPresenter(mainPresenter: MainPresenter) : Mvp.Presenter = mainPresenter

}
