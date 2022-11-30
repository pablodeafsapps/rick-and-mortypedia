package org.pablodeafsapps.rickandmortypedia.character.presentation.di

import dagger.Module
import dagger.Provides
import org.pablodeafsapps.rickandmortypedia.character.presentation.CharactersContract
import org.pablodeafsapps.rickandmortypedia.character.presentation.presenter.CharactersPresenter

@Module
class CharactersPresentationModule(private val charactersView: CharactersContract.View) {

    @Provides
    fun providesCharactersView() : CharactersContract.View = charactersView

    @Provides
    fun providesCharactersPresenter(charactersPresenter: CharactersPresenter) : CharactersContract.Presenter = charactersPresenter

}
