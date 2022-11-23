package org.pablodeafsapps.rickandmortypedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import org.pablodeafsapps.rickandmortypedia.character.data.di.CharactersDataModule
import org.pablodeafsapps.rickandmortypedia.character.di.CharactersComponent
import org.pablodeafsapps.rickandmortypedia.character.presentation.di.CharactersPresentationModule
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Mvp.View {

    @Inject
    lateinit var mainPresenter: Mvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        getCharactersComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun onPause() {
        super.onPause()
        mainPresenter.onViewPaused()
    }

    override fun showMessage() {
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener { mainPresenter.onClickmeOptionSelected(num = Math.random()) }

        val buttonRequest: Button = findViewById(R.id.button_coroutines)
        buttonRequest.setOnClickListener { mainPresenter.onLaunchRequestOptionSelected() }

        findViewById<Button>(R.id.button_parallel_coroutines).apply {
            setOnClickListener { mainPresenter.onLaunchSeveralRequestsOptionSelected() }
        }
    }

}

private fun MainActivity.getCharactersComponent(): CharactersComponent =
    (application as RickAndMortyApplication).provideCharactersComponentFactory()
        .create(
            presentationModule = CharactersPresentationModule(this),
            dataModule = CharactersDataModule(applicationContext)
        )
