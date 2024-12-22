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
import com.basitbhatti.buysalebooks.state.SignInState
import com.basitbhatti.buysalebooks.utils.EMAIL_ADDRESS
import com.basitbhatti.buysalebooks.utils.FULL_NAME
import com.basitbhatti.buysalebooks.utils.USERNAME
import com.basitbhatti.buysalebooks.utils.USERS
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pdftoexcel.bankstatementconverter.utils.PrefManager

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    state: SignInState,
    onSignInClick: () -> Unit
) {

    val context = LocalContext.current

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
                text = "Login",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 15.dp)
            )


            OutlinedTextField(modifier = Modifier
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
                })


            OutlinedTextField(modifier = Modifier
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
                    Icon(painter = if (passwordVisible) painterResource(R.drawable.password_1) else painterResource(
                        R.drawable.password_0
                    ), contentDescription = "",
                        modifier
                            .size(20.dp)
                            .clickable {
                                passwordVisible = !passwordVisible
                            })
                })

            Button(
                onClick = {
                    if (emailAddress.isNotEmpty() || password.isNotEmpty()) {
                        loginWithEmailPassword(context = context, emailAddress, password)
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
                Text(text = "Login")
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Don't have an account?")
                Spacer(Modifier.width(4.dp))
                Text("Sign up.",
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
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
                onClick = {
                    signInWithGoogle()
                },
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
                        "Log in with Google", fontWeight = FontWeight.Medium
                    )

                }
            }
        }

    }

}


fun loginWithEmailPassword(context: Context, email: String, pass: String) {

    val pref = PrefManager(context)

    val dialog = ProgressDialog(context)
    dialog.setMessage("Please wait while we log you in...")
    dialog.setCancelable(false)
    dialog.show()

    val auth = FirebaseAuth.getInstance()

    auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener {
        Log.d("TAGAuth", "loginWithEmailPassword: success")

        FirebaseDatabase.getInstance().getReference(USERS).child(email.replace(".", ""))
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("TAGAuth", "loginWithEmailPassword: success")

                    dialog.dismiss()

                    val user = snapshot.getValue() as User

                    pref.saveString(USERNAME, user.username)
                    pref.saveString(EMAIL_ADDRESS, user.email)
                    pref.saveString(FULL_NAME, user.fullName)
                }

                override fun onCancelled(error: DatabaseError) {
                    dialog.dismiss()
                    Toast.makeText(context, "Error occurred!", Toast.LENGTH_SHORT).show()
                    Log.d("TAGAuth", "loginWithEmailPassword: $it")

                }
            })


    }.addOnFailureListener {
        dialog.dismiss()
        Log.d("TAGAuth", "loginWithEmailPassword: $it")
        Toast.makeText(context, "Error occurred!", Toast.LENGTH_SHORT).show()
    }


}

fun signInWithGoogle(context: Context) {

    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(context.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(true)
                .build()
        ).build()


}

@Preview
@Composable
private fun Preview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
