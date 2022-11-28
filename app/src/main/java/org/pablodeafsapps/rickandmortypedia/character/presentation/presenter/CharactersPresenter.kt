package org.pablodeafsapps.rickandmortypedia.character.presentation.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.pablodeafsapps.rickandmortypedia.character.domain.CharactersDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.presentation.CharactersContract
import org.pablodeafsapps.rickandmortypedia.common.Mvp
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CharactersPresenter @Inject constructor(
    private val charactersView: CharactersContract.View,
    private val getAllCharactersUc: CharactersDomainLayerContract.PresentationLayer.UseCase
) : CharactersContract.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
    private var job: Job? = null

    override var view: Mvp.View? = charactersView

    override fun onViewCreated() {
        job = launch {
            getAllCharactersUc.getAllCharacters().onSuccess { characters ->
                charactersView.loadCharacters(data = characters)
            }.onFailure { th ->
                th.printStackTrace()
                charactersView.showMessage(msg = "Cannot load data")
            }
        }
    }

    override fun onViewDestroyed() {
        job?.cancel()
    }

}
