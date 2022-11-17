package org.pablodeafsapps.rickandmortypedia

import kotlinx.coroutines.*
import org.pablodeafsapps.rickandmortypedia.data.api.CharactersService
import org.pablodeafsapps.rickandmortypedia.data.model.CharactersDto
import org.pablodeafsapps.rickandmortypedia.data.utils.getRetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class MainPresenter(val mainView: Mvp.View) : Mvp.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    var greetings: String? = null
    private val retrofitInstance: Retrofit by lazy { getRetrofitInstance(converterFactory = GsonConverterFactory.create()) }
    private val charactersService: CharactersService by lazy { retrofitInstance.create(CharactersService::class.java) }

    override fun onClickmeOptionSelected(num: Double) {
        greetings = anotherFun()
        if (num > 0.5) {
            mainView.showMessage()
        } else {
            mainView.showLogMessage()
        }
    }

    override fun onLaunchRequestOptionSelected() {
        launch {
            try {
                val response: CharactersDto? = charactersService.getAllCharactersList()
                println(response?.toString())
            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }

    private fun anotherFun(): String = "Hello!"

}
