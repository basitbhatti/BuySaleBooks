package com.basitbhatti.buysalebooks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.basitbhatti.buysalebooks.R
import com.basitbhatti.buysalebooks.navigation.Screen
import com.pdftoexcel.bankstatementconverter.utils.PrefManager

@Composable
fun HomeScreen(modifier: Modifier = Modifier, controller: NavHostController) {

    val context = LocalContext.current

    val fontFamily = FontFamily(
        listOf(
            Font(resId = R.font.regular, weight = FontWeight.Normal),
            Font(resId = R.font.medium, weight = FontWeight.Medium),
            Font(resId = R.font.bold, weight = FontWeight.Bold),
            Font(resId = R.font.lite, weight = FontWeight.Light)
        )
    )

    val pref = PrefManager(context)

    var displayName by remember {
        mutableStateOf("John Doe")
    }

//    displayName = pref.getString(FULL_NAME)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                controller.navigate(Screen.AddPostScreen.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Post")
            }
        }

    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier.padding(start = 20.dp),
                ) {

                    Text(
                        text = "Welcome Back",
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )

                    Text(
                        text = displayName,
                        fontSize = 20.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            //onSearchClick
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                }
            }

        }
    }


}


@Preview
@Composable
private fun Preview() {
    HomeScreen(controller = rememberNavController())
}