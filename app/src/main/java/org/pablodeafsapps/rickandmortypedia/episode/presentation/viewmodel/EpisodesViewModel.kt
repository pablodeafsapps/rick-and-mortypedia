package org.pablodeafsapps.rickandmortypedia.episode.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    val getAllEpisodesUc: EpisodesDomainLayerContract.PresentationLayer.UseCase
) : ViewModel() {

    private val _episodes: MutableStateFlow<Episodes?> by lazy { MutableStateFlow(null) }
    val episodes: StateFlow<Episodes?> = _episodes.asStateFlow()

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

}
