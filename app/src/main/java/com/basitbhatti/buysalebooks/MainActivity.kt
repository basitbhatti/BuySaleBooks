package com.basitbhatti.buysalebooks

import NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.basitbhatti.buysalebooks.ui.theme.buysalebooksTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            buysalebooksTheme {
                val navController = rememberNavController()
                val viewModel = viewModel<SignInViewModel>()

                NavGraph(navController, viewModel)
            }
        }
    }


}
