package org.pablodeafsapps.rickandmortypedia.episode.presentation.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.pablodeafsapps.rickandmortypedia.common.Mvp
import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.episode.presentation.EpisodesContract
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EpisodesPresenter @Inject constructor(
    val episodesView: EpisodesContract.View,
    val getAllEpisodesUc: EpisodesDomainLayerContract.PresentationLayer.UseCase
) : EpisodesContract.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
    private var job: Job? = null

    override var view: Mvp.View? = episodesView

    override fun onViewCreated() {
        job = launch {
            getAllEpisodesUc.getAllEpisodes().onSuccess { episodes ->
                episodesView.loadEpisodes(data = episodes)
            }.onFailure { th ->
                th.printStackTrace()
                episodesView.showMessage(msg = "Cannot load data")
            }
        }
    }

    override fun onViewDestroyed() {
        job?.cancel()
    }

}
