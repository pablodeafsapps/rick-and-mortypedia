package org.pablodeafsapps.rickandmortypedia.episode.presentation

import org.pablodeafsapps.rickandmortypedia.common.Mvp
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes

interface EpisodesContract {

    interface View : Mvp.View {

        fun showMessage(msg: String)

        fun loadEpisodes(data: Episodes)

    }

    interface Presenter : Mvp.Presenter {

        fun onViewCreated()

        fun onViewDestroyed()

    }

}
