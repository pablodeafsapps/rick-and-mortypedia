package org.pablodeafsapps.rickandmortypedia.episode.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.pablodeafsapps.rickandmortypedia.RickAndMortyApplication
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.character.presentation.di.CharactersPresentationModule
import org.pablodeafsapps.rickandmortypedia.character.presentation.view.CharactersFragment
import org.pablodeafsapps.rickandmortypedia.databinding.FragmentDataCollectionBinding
import org.pablodeafsapps.rickandmortypedia.episode.di.EpisodesComponent
import org.pablodeafsapps.rickandmortypedia.episode.domain.model.Episodes
import org.pablodeafsapps.rickandmortypedia.episode.presentation.EpisodesContract
import org.pablodeafsapps.rickandmortypedia.episode.presentation.di.EpisodesPresentationModule

class EpisodesFragment : Fragment(), EpisodesContract.View {

    private var binding: FragmentDataCollectionBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentDataCollectionBinding.inflate(inflater, parent, false)
        .also {
            binding = it
            initViews()
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getEpisodesComponent().inject(this)
    }

    override fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun loadEpisodes(data: Episodes) {
        (binding?.rvData?.adapter as? EpisodesAdapter)?.updateData(newData = data.results)
    }

    private fun initViews() {
        with(binding?.rvData) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = EpisodesAdapter()
        }
    }

    companion object {
        fun newInstance(): EpisodesFragment = EpisodesFragment().apply { arguments = Bundle() }
    }

}

private fun EpisodesFragment.getEpisodesComponent(): EpisodesComponent =
    (requireContext().applicationContext as RickAndMortyApplication).provideEpisodesComponentFactory()
        .create(presentationModule = EpisodesPresentationModule(this))
