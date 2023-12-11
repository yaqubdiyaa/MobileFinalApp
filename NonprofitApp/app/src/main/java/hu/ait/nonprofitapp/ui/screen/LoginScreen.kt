package hu.ait.nonprofitapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    navController: NavController,
) {

    var showPassword by rememberSaveable { mutableStateOf(false) }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

   val coroutineScope = rememberCoroutineScope()

    Box() {
        Text(
            text = "GiveSwipe",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp),
            fontSize = 30.sp
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                label = {
                    Text(text = "username: ")
                },
                value = email,
                onValueChange = {
                    email = it
                },
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Default.Email, null)
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.8f),
                label = {
                    Text(text = "Password")
                },
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                visualTransformation =
                if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(Icons.Default.Password, null)
                },
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        if (showPassword) {
                            Icon(Icons.Default.Visibility, null)
                        } else {
                            Icon(Icons.Default.VisibilityOff, null)
                        }
                    }
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "Sign up", modifier = Modifier.clickable{
                    loginViewModel.registerUser(email,password)
                })

                OutlinedButton(onClick = {

                    navController.navigate("swipescreen")

                    /*
                    coroutineScope.launch {
                        val result = loginViewModel.loginUser(email,password)
                         if (result?.user != null) {
                            onLoginSuccess()
                        }
                    }

                     */



                }) {
                    Text(text = "Login")
                }

            }

        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (loginViewModel.loginUIState) {
                is LoginViewModel.LoginUiState.Init -> {}
                is LoginViewModel.LoginUiState.Loading -> CircularProgressIndicator()
                is LoginViewModel.LoginUiState.RegisterSuccess -> Text(text = "Registration OK")
                is LoginViewModel.LoginUiState.LoginSuccess -> Text(text = "Login OK")
                is LoginViewModel.LoginUiState.Error -> Text(text = "Error: ${
                    (loginViewModel.loginUIState as LoginViewModel.LoginUiState.Error).error
                }")
            }

        }
    }
}

//TODO: make sure after logging in, it takes you to the swiping page  (navbar)