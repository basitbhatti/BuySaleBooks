package com.basitbhatti.buysalebooks.utils

import com.basitbhatti.buysalebooks.model.User

data class SignInResult (
    val data : User?,
    val error: String?
)
