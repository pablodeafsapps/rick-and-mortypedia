package org.pablodeafsapps.rickandmortypedia.common

interface Mvp {

    interface View

    interface Presenter {

        var view: View?

        fun onViewDetached() {
            view = null
        }

    }

}
