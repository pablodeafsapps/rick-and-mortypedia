package org.pablodeafsapps.rickandmortypedia

class MainPresenter(val mainView: Mvp.View) : Mvp.Presenter {

    var greetings: String? = null

    override fun onClickmeOptionSelected(num: Double) {
        greetings = anotherFun()
        if (num > 0.5) {
            mainView.showMessage()
        } else {
            mainView.showLogMessage()
        }
    }


    private fun anotherFun(): String = "Hello!"

}
