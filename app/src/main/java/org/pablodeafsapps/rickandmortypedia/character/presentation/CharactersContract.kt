package org.pablodeafsapps.rickandmortypedia.character.presentation

import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import org.pablodeafsapps.rickandmortypedia.common.Mvp

interface CharactersContract {

    interface View : Mvp.View {

        fun showMessage(msg: String)

        fun loadCharacters(data: Characters)

    }

   interface Presenter : Mvp.Presenter {

       fun onViewCreated()

       fun onViewDestroyed()

    }

}
