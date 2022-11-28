package org.pablodeafsapps.rickandmortypedia.episode.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.pablodeafsapps.rickandmortypedia.episode.domain.EpisodesDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    val getAllEpisodesUc: EpisodesDomainLayerContract.PresentationLayer.UseCase
) : ViewModel() {

    val episodes: StateFlow<Episodes?>
        get() = _episodes
    private val _episodes: MutableStateFlow<Episodes?> by lazy {
        MutableStateFlow(null)
    }

    fun onViewCreated() {
        viewModelScope.launch {
            getAllEpisodesUc.getAllEpisodes().onSuccess { episodes ->
                _episodes.value = episodes
            }.onFailure { th ->
                th.printStackTrace()
            }
        }
    }

}
