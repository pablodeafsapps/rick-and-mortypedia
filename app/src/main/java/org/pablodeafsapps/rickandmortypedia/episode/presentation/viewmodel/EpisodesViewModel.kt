package org.pablodeafsapps.rickandmortypedia.episode.presentation.viewmodel

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes
import org.pablodeafsapps.rickandmortypedia.episode.domain.usecase.GetAllEpisodesUc
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val state: SavedStateHandle,
    val getAllEpisodesUc: EpisodesDomainLayerContract.PresentationLayer.UseCase
) : ViewModel() {

    val episodes: StateFlow<Episodes?>
        get() = _episodes.asStateFlow()
    private var _episodes: MutableStateFlow<Episodes?> = MutableStateFlow(null)

    init {
        fetchEpisodesData()
    }

    private fun fetchEpisodesData() {
        viewModelScope.launch {
            getAllEpisodesUc.getAllEpisodes().onSuccess { episodes ->
                _episodes.update { episodes }
            }.onFailure { th ->
                th.printStackTrace()
            }
        }
    }

    class Provider @Inject constructor(
        private val getAllEpisodesUc: GetAllEpisodesUc,
        owner: SavedStateRegistryOwner
    ) : AbstractSavedStateViewModelFactory(owner, null) {

        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T = EpisodesViewModel(handle, getAllEpisodesUc) as T

    }

}
