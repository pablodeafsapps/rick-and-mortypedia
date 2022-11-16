package org.pablodeafsapps.rickandmortypedia

interface Mvp {

    interface View {

        fun showMessage()

        fun showLogMessage() {
        }

    }

    interface Presenter {

        fun onClickmeOptionSelected(num: Double)

    }

}