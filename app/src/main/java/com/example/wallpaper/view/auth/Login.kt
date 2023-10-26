package com.example.wallpaper.view.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wallpaper.components.ReusableTextField
import com.example.wallpaper.viewModel.AuthViewModel

data class Input(
    val label: String,
    var value: String,
    val type: String,
    val error: String,
    val isPassword: Boolean,
    val hint: String = "",
)

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Login(navController: NavController) {

    val vm = AuthViewModel()
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val inputList = listOf(
        Input(
            label = "Email address",
            value = email,
            type = "email",
            error = "",
            isPassword = false,
            hint = "mahi@cric.com"
        ), Input(
            label = "Enter Password",
            value = password,
            type = "password",
            error = "",
            isPassword = true,
            hint = "********"
        )
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "Welcome back !",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.W800,
            fontSize = 24.sp
        )
        Text(
            text = "Enter your details to access your account",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.W500,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        inputList.forEach {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    "${it.label}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.W500
                )
                ReusableTextField(
                    input = it
                ) { newValue ->
                    when (it.label) {
                        "Email address" -> email = newValue
                        "Enter Password" -> password = newValue
                    }

                }

            }

        }

        Button(
            onClick = {
                vm.signInWithEmailAndPassword(email, password,navController)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(14.dp),
        ) {
            if (vm.loading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            } else {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Don't have an account?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,

                )
            TextButton(onClick = {
                navController.navigate("register")
            }) {
                Text(
                    "Sign up here",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,

                    )

            }
        }


    }
}