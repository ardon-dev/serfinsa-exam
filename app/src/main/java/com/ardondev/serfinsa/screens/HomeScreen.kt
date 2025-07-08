package com.ardondev.serfinsa.screens

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.navigation.NavController
import com.ardondev.serfinsa.CommerceDetailRoute
import com.ardondev.serfinsa.JoinCommerceRoute
import com.ardondev.serfinsa.MainViewModel
import com.ardondev.serfinsa.models.Status

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Comercios") }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(JoinCommerceRoute)
                }
            ) {
                Row {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Add),
                        contentDescription = "Add"
                    )
                    Text("Afiliar")
                }
            }
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            LazyColumn {
                items(
                    items = viewModel.commerces.value,
                    key = { e -> e.id }
                ) { commerce ->
                    ListItem(
                        supportingContent = {
                            Text(
                                commerce.status.title,
                                color = when (commerce.status) {
                                    Status.PENDING -> Color.Gray
                                    Status.ACCEPTED -> Color.Blue
                                    Status.REJECTED -> Color.Red
                                }
                            )
                        },
                        headlineContent = { Text(commerce.name) },
                        overlineContent = { Text("${commerce.municipality}, ${commerce.department}") },
                        leadingContent = {
                            Icon(
                                painter = rememberVectorPainter(
                                    Icons.Default.Star,
                                ),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.clickable {
                            navController.navigate(CommerceDetailRoute(id = commerce.id))
                        }
                    )
                }
            }
        }
    }
}