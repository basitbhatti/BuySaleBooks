package com.basitbhatti.buysalebooks.state

data class SignInState (
    val isSignInSuccessful : Boolean = false,
    val signInError : String? = null
)