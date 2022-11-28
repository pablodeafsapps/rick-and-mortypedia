package org.pablodeafsapps.rickandmortypedia.main

import org.pablodeafsapps.rickandmortypedia.common.Mvp

interface MainContract {

    interface View : Mvp.View {

        fun replaceWithCharactersFragment()

        fun replaceWithEpisodesFragment()

    }

    interface Presenter : Mvp.Presenter {

        fun onCharactersSelected()

        fun onEpisodesSelected()

    }

}
