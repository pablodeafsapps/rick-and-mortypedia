package org.pablodeafsapps.rickandmortypedia.login.domain.model

data class LoginUser(
    val name: String,
    val email: Email
)

@JvmInline
value class Email(val value: String)

@JvmInline
value class Keypass(val value: String)
