package org.pablodeafsapps.rickandmortypedia

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainPresenter @Inject constructor(
    val mainView: Mvp.View,
    val getAllCharactersUc: DomainLayerContract.PresentationLayer.UseCase
) : Mvp.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
    private var job: Job? = null

    override fun onViewCreated() {
        job = launch {
            getAllCharactersUc.getAllCharacters().onSuccess { characters ->
                mainView.loadCharacters(data = characters)
            }.onFailure { th ->
                th.printStackTrace()
                mainView.showMessage(msg = "Cannot load data")
            }
        }
    }

    override fun onViewPaused() {
        job?.cancel()
    }

}
