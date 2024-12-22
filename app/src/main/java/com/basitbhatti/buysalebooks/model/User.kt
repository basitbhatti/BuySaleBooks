package com.basitbhatti.buysalebooks.model

data class User(
    val userId : String,
    val username: String,
    val fullName: String,
    val email: String,
    val profileUrl : String?
)