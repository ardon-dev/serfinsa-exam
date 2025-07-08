package com.ardondev.serfinsa.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ardondev.serfinsa.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinCommerceScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.clear()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.ArrowBack),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text("Afiliar comercio")
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Spacer(Modifier.size(16.dp))
                TextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Nombre") },
                    value = viewModel.name.value,
                    onValueChange = { newValue ->
                        viewModel.validateCommerce()
                        viewModel.name.value = newValue
                    }
                )
                Spacer(Modifier.size(16.dp))
                TextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Departamento") },
                    value = viewModel.department.value,
                    onValueChange = { newValue ->
                        viewModel.validateCommerce()
                        viewModel.department.value = newValue
                    }
                )
                Spacer(Modifier.size(16.dp))
                TextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Municipio") },
                    value = viewModel.municipality.value,
                    onValueChange = { newValue ->
                        viewModel.validateCommerce()
                        viewModel.municipality.value = newValue
                    }
                )
                Spacer(Modifier.size(16.dp))
                TextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("IVA/NIT o DUI") },
                    value = viewModel.document.value,
                    onValueChange = { newValue ->
                        viewModel.validateCommerce()
                        viewModel.document.value = newValue
                    }
                )
                Spacer(Modifier.size(16.dp))
                TextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Telefono") },
                    value = viewModel.phone.value,
                    onValueChange = { newValue ->
                        viewModel.validateCommerce()
                        viewModel.phone.value = newValue
                    }
                )
                Spacer(Modifier.size(16.dp))
                TextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Correo electrónico") },
                    value = viewModel.email.value,
                    onValueChange = { newValue ->
                        viewModel.validateCommerce()
                        viewModel.email.value = newValue
                    }
                )
                Spacer(Modifier.size(24.dp))
                Button(
                    enabled = viewModel.validCommerce.value,
                    onClick = {
                        viewModel.joinCommerce()
                        navController.navigateUp()
                    }) {
                    Text("Afiliar")
                }
            }
        }
    }
}