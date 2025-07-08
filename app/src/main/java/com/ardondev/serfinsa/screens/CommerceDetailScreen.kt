package com.ardondev.serfinsa.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ardondev.serfinsa.MainViewModel
import com.ardondev.serfinsa.models.Status

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommerceDetailScreen(
    id: String,
    navController: NavController,
    viewModel: MainViewModel
) {
    val commerce = viewModel.commerces.value.first { it.id == id }

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
                title = { Text(commerce.name) }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                Modifier
                    .padding(24.dp)
                    .fillMaxSize()
                    .verticalScroll(
                        state = rememberScrollState()
                    )
            ) {
                LabelInfo("Nombre", commerce.name)
                HorizontalDivider(Modifier.fillMaxWidth())
                LabelInfo("Departamento", commerce.department)
                HorizontalDivider(Modifier.fillMaxWidth())
                LabelInfo("Municipio", commerce.municipality)
                HorizontalDivider(Modifier.fillMaxWidth())
                LabelInfo("Teléfono", commerce.phone)
                HorizontalDivider(Modifier.fillMaxWidth())
                LabelInfo("Correo electrónico", commerce.email)
                HorizontalDivider(Modifier.fillMaxWidth())
                LabelInfo("Documento", commerce.document)

                if (commerce.status == Status.PENDING) {
                    Spacer(Modifier.size(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = {
                            viewModel.approveCommerce(id)
                            navController.navigateUp()
                        }) {
                            Text("Aprobar")
                        }
                        Spacer(Modifier.size(16.dp))
                        Button(
                            onClick = {
                                viewModel.rejectCommerce(id)
                                navController.navigateUp()
                            }, colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                                contentColor = MaterialTheme.colorScheme.onErrorContainer
                            )
                        ) {
                            Text("Rechazar")
                        }
                    }
                } else {
                    Text(
                        commerce.status.title.toUpperCase(Locale.current),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = if (commerce.status == Status.ACCEPTED) Color.Blue else Color.Red,
                            fontWeight = FontWeight.Black
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun LabelInfo(
    label: String,
    value: String
) {
    Column {
        Spacer(Modifier.size(8.dp))
        Text(label, style = MaterialTheme.typography.labelMedium)
        Text(value)
        Spacer(Modifier.size(8.dp))
    }
}

