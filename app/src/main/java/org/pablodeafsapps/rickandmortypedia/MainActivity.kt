package org.pablodeafsapps.rickandmortypedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters
import org.pablodeafsapps.rickandmortypedia.character.presentation.di.CharactersPresentationModule
import org.pablodeafsapps.rickandmortypedia.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Mvp.View {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var mainPresenter: Mvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        getCharactersComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        mainPresenter.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.onViewPaused()
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
    }

    override fun loadCharacters(data: Characters) {
        // TODO: access 'RecyclerView' adapter and load 'data'
    }

    private fun initViews() {
        // TODO: inint 'RecyclerView' using adapter and 'LinearLayoutManager'

    }

}

private fun MainActivity.getCharactersComponent(): CharactersComponent =
    (application as RickAndMortyApplication).provideCharactersComponentFactory()
        .create(presentationModule = CharactersPresentationModule(this))
