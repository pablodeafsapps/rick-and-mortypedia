package org.pablodeafsapps.rickandmortypedia.login.domain

import org.pablodeafsapps.rickandmortypedia.login.domain.model.Email
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Keypass
import org.pablodeafsapps.rickandmortypedia.login.domain.model.LoginUser

interface LoginDomainLayerContract {

    interface PresentationLayer {

        interface UseCase<T> {

            suspend operator fun invoke(email: Email, keypass: Keypass): Result<T>

        }

    }

    interface DataLayer {

        interface LoginRepository {

            suspend fun loginUser(email: Email, keypass: Keypass): Result<LoginUser>

        }

    }

}
