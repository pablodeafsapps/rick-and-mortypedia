package org.pablodeafsapps.rickandmortypedia

import org.pablodeafsapps.rickandmortypedia.character.domain.model.Characters

interface Mvp {

    interface View {

        fun showMessage()

        fun showLogMessage() {
        }

        fun loadCharacters(data: Characters)

        fun showErrorMessage(msg: String)

    }

    interface Presenter {

        fun onClickmeOptionSelected(num: Double)

        fun onLaunchRequestOptionSelected()

        fun onLaunchSeveralRequestsOptionSelected()

        fun onViewPaused()

    }

}
