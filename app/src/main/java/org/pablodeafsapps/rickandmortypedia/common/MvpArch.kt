package org.pablodeafsapps.rickandmortypedia

import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters

interface Mvp {

    interface View {

        fun showMessage(msg: String)

        fun loadCharacters(data: Characters)

    }

    interface Presenter {

        fun onViewCreated()

        fun onViewPaused()

    }

}
