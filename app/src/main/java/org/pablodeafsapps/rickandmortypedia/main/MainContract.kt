package org.pablodeafsapps.rickandmortypedia.main

import org.pablodeafsapps.rickandmortypedia.common.Mvp

interface MainContract {

    interface View : Mvp.View

    interface Presenter : Mvp.Presenter {

        fun onCharactersSelected()

        fun onEpisodesPresenter()

    }

}
