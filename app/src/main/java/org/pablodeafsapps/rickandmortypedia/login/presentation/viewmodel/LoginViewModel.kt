package org.pablodeafsapps.rickandmortypedia.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.pablodeafsapps.rickandmortypedia.login.domain.LoginDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Email
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Keypass
import org.pablodeafsapps.rickandmortypedia.login.domain.model.LoginUser
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUserWithEmailAndKeypassUc: LoginDomainLayerContract.PresentationLayer.UseCase<LoginUser>
) : ViewModel() {

    val loginUser: StateFlow<LoginUser?>
        get() = _loginUser.asStateFlow()
    private var _loginUser: MutableStateFlow<LoginUser?> = MutableStateFlow(null)

    fun onLoginOptionSelected(email: Email, keypass: Keypass) {
        viewModelScope.launch {
            loginUserWithEmailAndKeypassUc.invoke(email = email, keypass = keypass).onSuccess { user ->
                _loginUser.value = user
            }.onFailure { th ->
                th.printStackTrace()
                _loginUser.value = getDummyLoginUser()
            }
        }
    }

    private fun getDummyLoginUser() =
        LoginUser(
            name = "",
            email = Email(value = "")
        )

}
