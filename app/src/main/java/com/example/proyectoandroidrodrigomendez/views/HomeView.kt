package com.example.proyectoandroidrodrigomendez.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.example.proyectoandroidrodrigomendez.components.Card
import com.example.proyectoandroidrodrigomendez.components.EmptyList
import com.example.proyectoandroidrodrigomendez.components.TopBar
import com.example.proyectoandroidrodrigomendez.model.ContactModel
import com.example.proyectoandroidrodrigomendez.viewModel.DirectoryViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun HomeView(navController: NavController, directoryVM: DirectoryViewModel) {
    var searchText by remember { mutableStateOf("") }
    val contactosList by directoryVM.contactsList.collectAsState()

    Scaffold(
        topBar = { TopBar("Números") },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("AddContact") }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            if (
                contactosList.isEmpty()
            ) {
                EmptyList("Sin Contactos")
            } else {
                ContentHomeView(navController, directoryVM)
            }
        }
    }
}


@Composable
fun ContentHomeView(
    navController: NavController,
    directoryVM: DirectoryViewModel
) {
    val contactsList by directoryVM.contactsList.collectAsState(initial = emptyList())

    var deleteContact by remember { mutableStateOf<ContactModel?>(null) }

    if (deleteContact != null) {
        AlertDialog(
            onDismissRequest = { deleteContact = null },
            title = { Text("Eliminar contacto") },
            text = { Text("¿Estás seguro de que querés eliminar este contacto?") },
            confirmButton = {
                TextButton(onClick = {
                    directoryVM.deleteContact(deleteContact!!)
                    deleteContact = null
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { deleteContact = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Column(modifier = Modifier) {
        LazyColumn {
            items(contactsList, key = { it.id }) { ctt ->
                val deleteAction = SwipeAction(
                    onSwipe = { deleteContact = ctt }, // ← mostramos confirmación
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red
                )
                val editAction = SwipeAction(
                    onSwipe = { navController.navigate("EditView/${ctt.id}") },
                    icon = rememberVectorPainter(Icons.Default.Edit),
                    background = Color.Blue
                )

                SwipeableActionsBox(
                    endActions = listOf(deleteAction),
                    startActions = listOf(editAction)
                ) {
                    Card(
                        fullname = "${ctt.name} ${ctt.lastName} ${ctt.secondName}",
                        email = ctt.email,
                        number = ctt.number,
                        onClick = {
                            navController.navigate("EditView/${ctt.id}")
                        }
                    )
                }
            }
        }
    }
}




