package org.pablodeafsapps.rickandmortypedia.login.domain.usecase

import android.nfc.FormatException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.pablodeafsapps.rickandmortypedia.login.domain.LoginDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Email
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Keypass
import org.pablodeafsapps.rickandmortypedia.login.domain.model.LoginUser
import org.pablodeafsapps.rickandmortypedia.login.domain.utils.isValid
import javax.inject.Inject

class LoginUserWithEmailAndKeypassUc @Inject constructor(
    private val loginRepository: LoginDomainLayerContract.DataLayer.LoginRepository
) : LoginDomainLayerContract.PresentationLayer.UseCase<LoginUser> {

    override suspend fun invoke(email: Email, keypass: Keypass): Result<LoginUser> =
        withContext(Dispatchers.IO) {
            if (email.isValid() && keypass.isValid()) {
                loginRepository.loginUser(
                    email = Email(value = email.value.trim()),
                    keypass = Keypass(value = keypass.value.trim())
                )
            } else {
                Result.failure(FormatException("E-mail or/and keypass are incorrect"))
            }
        }

}
