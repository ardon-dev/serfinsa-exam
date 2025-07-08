package com.ardondev.serfinsa.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ardondev.serfinsa.HomeRoute

@Composable
fun LoginScreen(
    navController: NavController
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = PaddingValues(24.dp))
    ) {
        Text("Inicio de Sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.size(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email.value,
            label = { Text("Correo electrónico") },
            onValueChange = { newValue ->
                email.value = newValue
            }
        )
        Spacer(Modifier.size(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            value = password.value,
            onValueChange = { newValue ->
                password.value = newValue
            }
        )
        Spacer(Modifier.size(24.dp))
        Button(
            enabled = email.value.isNotEmpty() && password.value.isNotEmpty(),
            onClick = {
                navController.navigate(HomeRoute)
            }
        ) {
            Text("Login")
        }
    }
}