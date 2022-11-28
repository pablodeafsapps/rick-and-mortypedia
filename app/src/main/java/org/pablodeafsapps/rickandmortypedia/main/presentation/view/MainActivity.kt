package org.pablodeafsapps.rickandmortypedia.main.presentation.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import org.pablodeafsapps.rickandmortypedia.R
import org.pablodeafsapps.rickandmortypedia.RickAndMortyApplication
import org.pablodeafsapps.rickandmortypedia.character.presentation.view.CharactersFragment
import org.pablodeafsapps.rickandmortypedia.databinding.ActivityMainBinding
import org.pablodeafsapps.rickandmortypedia.main.MainContract
import org.pablodeafsapps.rickandmortypedia.main.di.MainComponent
import org.pablodeafsapps.rickandmortypedia.main.presentation.di.MainPresentationModule
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnItemSelectedListener, MainContract.View {

    @Inject
    lateinit var mainPresenter: MainContract.Presenter
    private lateinit var binding: ActivityMainBinding
    private val charactersFragment: Fragment by lazy { CharactersFragment.newInstance() }
    private val episodesFragment: Fragment by lazy { CharactersFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.onViewDetached()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.characters -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, charactersFragment).commit()
                return true
            }
            R.id.episodes -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, episodesFragment).commit()
                return true
            }
        }
        return false
    }

    private fun initViews() {
        with(binding.bottomNavigationView) {
            setOnItemSelectedListener(this@MainActivity)
            selectedItemId = R.id.characters
        }
    }

}

private fun MainActivity.getApplicationComponent(): MainComponent =
    (application as RickAndMortyApplication).provideMainComponentFactory()
        .create(presentationModule = MainPresentationModule(this))
