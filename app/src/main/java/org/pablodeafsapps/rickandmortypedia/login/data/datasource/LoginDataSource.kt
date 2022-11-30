package org.pablodeafsapps.rickandmortypedia.login.data.datasource

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import org.pablodeafsapps.rickandmortypedia.login.data.model.LoginUserDto
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Email
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Keypass
import javax.inject.Inject

interface LoginDataSource {

    suspend fun signInWithEmailAndPassword(email: Email, keypass: Keypass) : Result<LoginUserDto>

}

class FirebaseAuthDataSource @Inject constructor(
    private val fbAuthInstance: FirebaseAuth
) : LoginDataSource {

    override suspend fun signInWithEmailAndPassword(
        email: Email, keypass: Keypass
    ): Result<LoginUserDto> =
        fbAuthInstance.signInWithEmailAndPassword(email.value, keypass.value).await()
            .runCatching { toLoginUserDto() }

}

private fun AuthResult.toLoginUserDto(): LoginUserDto =
    LoginUserDto(
        name = user?.displayName,
        email = user?.email
    )
