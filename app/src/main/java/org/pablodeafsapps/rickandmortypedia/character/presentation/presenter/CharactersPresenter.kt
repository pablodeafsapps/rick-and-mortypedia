package org.pablodeafsapps.rickandmortypedia.character.presentation.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.pablodeafsapps.rickandmortypedia.character.domain.CharactersDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import org.pablodeafsapps.rickandmortypedia.character.presentation.CharactersContract
import org.pablodeafsapps.rickandmortypedia.common.Mvp
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class CharactersPresenter @Inject constructor(
    val charactersView: CharactersContract.View,
    @Named("get_all_characters") val getAllCharactersUc: CharactersDomainLayerContract.PresentationLayer.UseCase<Characters>,
    @Named("get_characters_next_page") val getCharactersNextPageUc: CharactersDomainLayerContract.PresentationLayer.UseCase<Characters>
) : CharactersContract.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
    // TODO: create 'Job' collection to handle several tasks simultaneously
    private var job: Job? = null

    override var view: Mvp.View? = charactersView

    override fun onViewCreated() {
        job = launch {
            // getAllCharactersUc.invoke()
            getAllCharactersUc().onSuccess { characters ->
                charactersView.loadCharacters(data = characters)
            }.onFailure { th ->
                th.printStackTrace()
                charactersView.showMessage(msg = "Cannot load data")
            }
        }
    }

    override fun onEndOfScrollReached() {
        job = launch {
            getCharactersNextPageUc().onSuccess { characters ->
                charactersView.loadCharacters(data = characters)
            }.onFailure { th ->
                th.printStackTrace()
            }
        }
    }

    override fun onViewDestroyed() {
        job?.cancel()
    }

}
