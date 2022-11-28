package org.pablodeafsapps.rickandmortypedia.episode.presentation.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.pablodeafsapps.rickandmortypedia.common.Mvp
import org.pablodeafsapps.rickandmortypedia.episode.presentation.EpisodesContract
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EpisodesPresenter @Inject constructor(
    private val episodesView: EpisodesContract.View
) : EpisodesContract.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
    private var job: Job? = null

    override var view: Mvp.View? = episodesView

    override fun onViewCreated() {
    }

    override fun onViewDestroyed() {
        job?.cancel()
    }

}
