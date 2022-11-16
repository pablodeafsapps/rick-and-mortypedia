package org.pablodeafsapps.rickandmortypedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), Mvp.View {

    private lateinit var mainPresenter: Mvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(mainView = this)
        initViews()
    }

    override fun showMessage() {
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener { mainPresenter.onClickmeOptionSelected(num = Math.random()) }
    }

}
