package org.pablodeafsapps.rickandmortypedia.login.data.repository

import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.pablodeafsapps.rickandmortypedia.login.data.datasource.LoginDataSource
import org.pablodeafsapps.rickandmortypedia.login.data.model.LoginUserDto
import org.pablodeafsapps.rickandmortypedia.login.domain.LoginDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Email
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Keypass
import org.pablodeafsapps.rickandmortypedia.login.domain.model.LoginUser
import org.pablodeafsapps.rickandmortypedia.login.domain.utils.toLoginUser

object RickAndMortyLoginRepository : LoginDomainLayerContract.DataLayer.LoginRepository {

    lateinit var loginDataSource: LoginDataSource

    override suspend fun loginUser(email: Email, keypass: Keypass): Result<LoginUser> =
        try {
            loginDataSource.signInWithEmailAndPassword(email = email, keypass = keypass)
                .map { loginUserDto -> loginUserDto.toLoginUser() }
        } catch(e: Exception) {
            Result.failure(e)
        }

}
