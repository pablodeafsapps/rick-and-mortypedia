package org.pablodeafsapps.rickandmortypedia.main.presentation.presenter

import org.pablodeafsapps.rickandmortypedia.common.Mvp
import org.pablodeafsapps.rickandmortypedia.main.MainContract
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val mainView: MainContract.View
) : MainContract.Presenter {

    override var view: Mvp.View? = mainView

    override fun onCharactersSelected() {
        TODO("Not yet implemented")
    }

    override fun onEpisodesPresenter() {
        TODO("Not yet implemented")
    }

}
