package org.pablodeafsapps.rickandmortypedia

import kotlinx.coroutines.*
import org.pablodeafsapps.rickandmortypedia.character.domain.DomainLayerContract
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainPresenter @Inject constructor(
    private val mainView: Mvp.View,
    private val getAllCharactersUc: DomainLayerContract.PresentationLayer.UseCase
) : Mvp.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
    var greetings: String? = null
    private var job: Job? = null
//    private val retrofitInstance: Retrofit by lazy { getRetrofitInstance(converterFactory = GsonConverterFactory.create()) }
//    private val charactersService: CharactersService by lazy { retrofitInstance.create(CharactersService::class.java) }
//    private val getAllCharactersUc: DomainLayerContract.PresentationLayer.UseCase by lazy { GetAllCharactersUc() }

    override fun onClickmeOptionSelected(num: Double) {
        greetings = anotherFun()
        if (num > 0.5) {
            mainView.showMessage()
        } else {
            mainView.showLogMessage()
        }
    }

    override fun onLaunchRequestOptionSelected() {
        job = launch {

//            getAllCharactersUc.getAllCharacters().mapCatching {  }
            getAllCharactersUc.getAllCharacters().fold(
                onSuccess = { characters ->
                    println(characters.toString())
                    greetings = characters.toString()
                },
                onFailure = { t ->
                    println(t.printStackTrace())
                }
            )


//            try {
//                val characters: Characters = getAllCharactersUc.getAllCharacters()
//                val response: CharactersDto? = charactersService.getAllCharactersList()
//                println(characters.toString())
//                greetings = characters.toString()
//            } catch (e: Exception) {
//                println(e.printStackTrace())
//            }
        }
    }

    override fun onLaunchSeveralRequestsOptionSelected() {
//        launch {
//            val a: Deferred<CharactersDto?> = async(Dispatchers.IO) {
//                Thread.sleep(5_000)
//                println("${Thread.currentThread().name}-a")
//                charactersService.getAllCharactersList()
//            }
//            val b: Deferred<CharactersDto?> = async(Dispatchers.IO) {
//                println(Thread.currentThread().name)
//                charactersService.getAllCharactersList()
//            }
//
//            println("${Thread.currentThread().name}-launch")
//            println((a.await()?.info?.count ?: 0) + (b.await()?.info?.pages ?: 0))
//        }
    }

    override fun onViewPaused() {
        job?.cancel()
    }

    private fun anotherFun(): String = "Hello!"

}
