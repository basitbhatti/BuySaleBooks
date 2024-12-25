package com.basitbhatti.buysalebooks.navigation

sealed class Screen (val route : String) {

    object SignupScreen : Screen(route = "signup_screen")
    object LoginScreen : Screen(route = "login_screen")
    object HomeScreen : Screen(route = "home_screen")
    object AddPostScreen : Screen(route = "add_post_screen")

}