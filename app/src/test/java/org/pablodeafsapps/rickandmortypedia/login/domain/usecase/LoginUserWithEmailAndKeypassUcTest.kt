package org.pablodeafsapps.rickandmortypedia.login.domain.usecase

import android.nfc.FormatException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.pablodeafsapps.rickandmortypedia.login.domain.LoginDomainLayerContract
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Email
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Keypass
import org.pablodeafsapps.rickandmortypedia.login.domain.model.LoginUser

@OptIn(ExperimentalCoroutinesApi::class)
class LoginUserWithEmailAndKeypassUcTest {

    private lateinit var sut: LoginDomainLayerContract.PresentationLayer.UseCase<LoginUser>
    private lateinit var mockLoginRepository: LoginDomainLayerContract.DataLayer.LoginRepository

    @Before
    fun setUp() {
        mockLoginRepository = Mockito.mock(LoginDomainLayerContract.DataLayer.LoginRepository::class.java)
        sut = LoginUserWithEmailAndKeypassUc(loginRepository = mockLoginRepository)
    }

    @Test
    fun `When 'Email' and 'Keypass' are valid, 'loginUser' is invoked`() = runTest {
        // Given
        val validEmail: Email = Email(value = "whatever@anything.com")
        val validKeypass: Keypass = Keypass(value = "abcdefgh")
        // When
        sut(email = validEmail, keypass = validKeypass)
        // Then
        Mockito.verify(mockLoginRepository).loginUser(email = validEmail, keypass = validKeypass)
    }

    @Test
    fun `When 'Email' and-or 'Keypass' are not valid, 'Result failure' is returned`() = runTest {
        // Given
        val invalidEmail: Email = Email(value = "whatever")
        val invalidKeypass: Keypass = Keypass(value = "1231")
        // When
        val result: Result<LoginUser> = sut(email = invalidEmail, keypass = invalidKeypass)
        // Then
        Assert.assertTrue(result.isFailure && result.exceptionOrNull() is FormatException)
    }

}
