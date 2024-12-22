package com.basitbhatti.buysalebooks.ui.screens

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.basitbhatti.buysalebooks.R
import com.basitbhatti.buysalebooks.model.User
import com.basitbhatti.buysalebooks.navigation.Screen
import com.basitbhatti.buysalebooks.utils.EMAIL_ADDRESS
import com.basitbhatti.buysalebooks.utils.FULL_NAME
import com.basitbhatti.buysalebooks.utils.USERNAME
import com.basitbhatti.buysalebooks.utils.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.pdftoexcel.bankstatementconverter.utils.PrefManager

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val context = LocalContext.current

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
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.vector_login),
                    modifier = modifier.size(200.dp),
                    contentDescription = ""
                )
            }

            Text(
                text = "Sign up",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 15.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = fullName,
                onValueChange = {
                    fullName = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = {
                    Text(text = "Enter Full Name")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                ),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "")
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                value = emailAddress,
                onValueChange = {
                    emailAddress = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = {
                    Text(text = "Enter Email Address")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                ),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "")
                }
            )


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                value = password,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    password = it
                },
                label = {
                    Text(text = "Enter Password")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                ),
                trailingIcon = {
                    Icon(
                        painter = if (passwordVisible) painterResource(R.drawable.password_1) else painterResource(
                            R.drawable.password_0
                        ),
                        contentDescription = "",
                        modifier
                            .size(20.dp)
                            .clickable {
                                passwordVisible = !passwordVisible
                            })
                }
            )

            Button(
                onClick = {
                    if (emailAddress.isNotEmpty() or password.isNotEmpty() or fullName.isNotEmpty()) {
                        signUpWithEmailPassword(context, fullName, emailAddress, password)
                        fullName = ""
                        emailAddress = ""
                        password = ""
                    } else {
                        Toast.makeText(context, "Empty Fields", Toast.LENGTH_SHORT).show()
                    }

                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF248CAD)
                ),
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(text = "Sign up")
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Already have an account?")
                Spacer(Modifier.width(4.dp))
                Text(
                    "Log in.",
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.LoginScreen.route)
                    }
                )
            }

            Text(
                "Or"
            )

            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 15.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE7E7E7)
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Image(
                        painter = painterResource(R.drawable.ic_google),
                        contentDescription = "google icon",
                        modifier = Modifier.size(30.dp),
                    )

                    Spacer(Modifier.width(10.dp))

                    Text(
                        "Log in with Google",
                        fontWeight = FontWeight.Medium
                    )

                }
            }
        }
    }

}

fun signUpWithEmailPassword(
    context: Context,
    fullName: String,
    emailAddress: String,
    password: String
) {


    val pref = PrefManager(context)

    val auth = FirebaseAuth.getInstance()
    val realtimeDatabase = FirebaseDatabase.getInstance()
    val userRef = realtimeDatabase.getReference(USERS)

    val progressDialog = ProgressDialog(context)
    progressDialog.setMessage("Please wait while we log you in..")
    progressDialog.setCancelable(false)
    progressDialog.show()

    auth.createUserWithEmailAndPassword(emailAddress, password).addOnSuccessListener {
        Log.d("TAGAUTH", "Success")

        val user = User(emailAddress.replace(".", ""), fullName, emailAddress)

        userRef.child(user.username).setValue(user).addOnSuccessListener {
            Log.d("TAGAUTH", "Success setValue")

            pref.saveString(USERNAME, user.username)
            pref.saveString(EMAIL_ADDRESS, user.email)
            pref.saveString(FULL_NAME, user.fullName)

            progressDialog.dismiss()
            Toast.makeText(context, "Success.", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            progressDialog.dismiss()
            val user = auth.currentUser
            user?.delete()
            Log.d("TAGAUTH", "ee: $it")
            Toast.makeText(context, "Could not sign up.", Toast.LENGTH_SHORT).show()

        }

    }.addOnFailureListener {
        progressDialog.dismiss()
        Toast.makeText(context, "Could not sign up.", Toast.LENGTH_SHORT).show()
        Log.d("TAGAUTH", "e: $it")
    }

}


@Preview
@Composable
fun Preview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    SignupScreen(navController = navController)
}