package com.basitbhatti.buysalebooks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AddPostScreen(modifier: Modifier = Modifier, controller: NavHostController) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

    }

}


@Preview
@Composable
private fun Preview() {
    AddPostScreen(controller = rememberNavController())
}