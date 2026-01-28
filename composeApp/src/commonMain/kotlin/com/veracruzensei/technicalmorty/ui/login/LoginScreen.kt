package com.veracruzensei.technicalmorty.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuempresa.tuapp.generated.resources.Res
import com.tuempresa.tuapp.generated.resources.image_black_door
import com.tuempresa.tuapp.generated.resources.image_white_door
import com.veracruzensei.technicalmorty.ui.core.colors.BackgroundPrimaryColor
import com.veracruzensei.technicalmorty.ui.core.colors.ButtonColor
import com.veracruzensei.technicalmorty.ui.core.colors.FontPrimaryColor
import com.veracruzensei.technicalmorty.ui.core.colors.UnfocusedContainerColorTextField
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LoginScreen(
    navigateToHome: () -> Unit = {}
) {

    val loginViewModel = koinViewModel<LoginViewModel>()
    val state by loginViewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        loginViewModel.uiEvent.collect { event ->
            when (event) {
                is LoginUiEvent.NavigateToHome -> navigateToHome()
                is LoginUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundPrimaryColor)
                .padding(it)
        ) {
            ImageDoor()
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Spacer(modifier = Modifier.size(20.dp))
                TextWelcomeToApp()
                Spacer(modifier = Modifier.size(20.dp))
                InputTextUser(
                    user = state.user,
                    onValueChange = { loginViewModel.updateUserInput(it) }
                )
                Spacer(modifier = Modifier.size(20.dp))
                InputTextPassword(
                    password = state.password,
                    onValueChange = { loginViewModel.updatePasswordInput(it) }
                )
                Spacer(modifier = Modifier.size(20.dp))
                ButtonLogin() {
                    loginViewModel.checkCredentials()
                }
            }
        }
    }
}

@Composable
fun ButtonLogin(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor,
            contentColor = FontPrimaryColor
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "Login",
            fontSize = 16.sp
        )
    }
}

@Composable
fun InputTextPassword(
    password: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = "Password",
                color = FontPrimaryColor.copy(0.5f)
            )
        },
        //visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedTextColor = FontPrimaryColor,
            unfocusedTextColor = FontPrimaryColor.copy(0.5f),
            unfocusedContainerColor = UnfocusedContainerColorTextField,
            focusedContainerColor = UnfocusedContainerColorTextField
        )
    )
}

@Composable
fun InputTextUser(
    user: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = user,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = "Email/Username",
                color = FontPrimaryColor.copy(0.5f)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = FontPrimaryColor,
            unfocusedTextColor = FontPrimaryColor.copy(0.5f),
            unfocusedContainerColor = UnfocusedContainerColorTextField,
            focusedContainerColor = UnfocusedContainerColorTextField
        )
    )
}

@Composable
fun TextWelcomeToApp() {
    Text(
        text = "Welcome to the Rick and Morty Universe",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        color = FontPrimaryColor
    )
}

@Composable
fun ImageDoor() {
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painterResource(
            if (isSystemInDarkTheme()) Res.drawable.image_black_door
            else Res.drawable.image_white_door
        ),
        contentDescription = "image door"
    )
}
