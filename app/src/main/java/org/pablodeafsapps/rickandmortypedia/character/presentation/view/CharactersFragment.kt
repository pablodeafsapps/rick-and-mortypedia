package org.pablodeafsapps.rickandmortypedia.character.presentation.view

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
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import org.pablodeafsapps.rickandmortypedia.character.presentation.CharactersContract
import org.pablodeafsapps.rickandmortypedia.character.presentation.di.CharactersPresentationModule
import org.pablodeafsapps.rickandmortypedia.databinding.FragmentDataCollectionBinding
import javax.inject.Inject

class CharactersFragment : Fragment(), CharactersContract.View {

    @Inject
    lateinit var charactersPresenter: CharactersContract.Presenter
    private var binding: FragmentDataCollectionBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getCharactersComponent().inject(this)
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
        charactersPresenter.onViewDestroyed()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        charactersPresenter.onViewCreated()
    }

    override fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun loadCharacters(data: Characters) {
        (binding?.rvData?.adapter as? CharactersAdapter)?.updateData(newData = data.results)
    }

    private fun initViews() {
        with(binding?.rvData) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = CharactersAdapter()
        }
    }

    companion object {
        fun newInstance(): CharactersFragment = CharactersFragment().apply { arguments = Bundle() }
    }

}

private fun CharactersFragment.getCharactersComponent(): CharactersComponent =
    (requireContext().applicationContext as RickAndMortyApplication).provideCharactersComponentFactory()
        .create(presentationModule = CharactersPresentationModule(this))
