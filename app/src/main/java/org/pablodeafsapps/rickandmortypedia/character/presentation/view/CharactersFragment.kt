package org.pablodeafsapps.rickandmortypedia.character.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.pablodeafsapps.rickandmortypedia.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment() {

    private var binding: FragmentCharactersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentCharactersBinding.inflate(inflater, parent, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun newInstance(): CharactersFragment = CharactersFragment().apply { arguments = Bundle() }

}
