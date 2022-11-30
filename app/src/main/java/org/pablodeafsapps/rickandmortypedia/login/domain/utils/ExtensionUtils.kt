package org.pablodeafsapps.rickandmortypedia.login.domain.utils

import org.pablodeafsapps.rickandmortypedia.login.data.model.LoginUserDto
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Email
import org.pablodeafsapps.rickandmortypedia.login.domain.model.Keypass
import org.pablodeafsapps.rickandmortypedia.login.domain.model.LoginUser

private const val DEFAULT_STRING_VALUE: String = ""

fun Email.isValid(): Boolean {
    val pattern = """.+@.+\.com""".toRegex()
    return value.trim().contains(pattern)
}

private const val KEYPASS_LENGTH: Int = 5
fun Keypass.isValid(): Boolean = value.trim().length > KEYPASS_LENGTH

fun LoginUserDto.toLoginUser() =
    LoginUser(
        name = name ?: DEFAULT_STRING_VALUE,
        email = Email(value = email ?: DEFAULT_STRING_VALUE)
    )

fun LoginUser?.isValid(): Boolean =
    this?.takeIf { it.name.isNotBlank() || it.email.value.isNotBlank() } != null
