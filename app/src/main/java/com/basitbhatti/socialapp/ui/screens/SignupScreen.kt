package com.basitbhatti.socialapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.basitbhatti.socialapp.R

@Composable
fun SignupScreen(modifier: Modifier = Modifier) {


    var fullName by remember {
        mutableStateOf("")
    }


    var emailAddress by remember {
        mutableStateOf("")
    }


    var password by remember {
        mutableStateOf("")
    }


    var passwordVisible by remember {
        mutableStateOf(false)
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.vector_login),
                    modifier = modifier.size(200.dp),
                    contentDescription = ""
                )
            }



            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(8.dp)),
                value = fullName,
                onValueChange = {
                    fullName = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = {
                    Text(text = "Enter Full Name")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.LightGray,
                    focusedContainerColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "")
                }
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    .clip(RoundedCornerShape(8.dp)),
                value = emailAddress,
                onValueChange = {
                    emailAddress = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = {
                    Text(text = "Enter Email Address")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.LightGray,
                    focusedContainerColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "")
                }
            )


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    .clip(RoundedCornerShape(8.dp)),
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    password = it
                },
                label = {
                    Text(text = "Enter Password")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.LightGray,
                    focusedContainerColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    Icon(painter = painterResource(R.drawable.), contentDescription = "", modifier.clickable {
                        passwordVisible = !passwordVisible
                    })
                }
            )


        }


    }


}


@Preview
@Composable
fun Preview(modifier: Modifier = Modifier) {
    SignupScreen()
}