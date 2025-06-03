package com.example.proyectoandroidrodrigomendez.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoandroidrodrigomendez.components.Form
import com.example.proyectoandroidrodrigomendez.components.TopBar
import com.example.proyectoandroidrodrigomendez.dataStore.ContactState
import com.example.proyectoandroidrodrigomendez.model.ContactModel
import com.example.proyectoandroidrodrigomendez.viewModel.DirectoryViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditView(
    navController: NavController, directoryVM: DirectoryViewModel, contactId: Long
) {
    val contact by remember { mutableStateOf<ContactModel?>(null) }
    val state = directoryVM.state.value
    var error by remember { mutableStateOf("") }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPhone(number: String): Boolean {
        return number.matches(Regex("^\\d{10,15}$"))
    }

    LaunchedEffect(contactId) {
        directoryVM.repository.getContactById(contactId).collectLatest { c ->
            if (c != null) {
                directoryVM.onNameChange(c.name)
                directoryVM.onLastNameChange(c.lastName)
                directoryVM.onSecondNameChange(c.secondName)
                directoryVM.onEmailChange(c.email)
                directoryVM.onNumberChange(c.number)
            }
        }
    }
    Scaffold(
        topBar = { TopBar("Editar Contacto", onNavClick = {navController.popBackStack()})}
    ){
            paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(30.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                value = state.name,
                onValueChange = directoryVM::onNameChange,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = state.lastName,
                onValueChange = directoryVM::onLastNameChange,
                label = { Text("Apellido Paterno") },
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = state.secondName,
                onValueChange = directoryVM::onSecondNameChange,
                label = { Text("Apellido Materno") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.email,
                onValueChange = directoryVM::onEmailChange,
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.number,
                onValueChange = directoryVM::onNumberChange,
                label = { Text("Numero") },
                modifier = Modifier.fillMaxWidth()
            )

            if (error.isNotBlank()) {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = {
                    if (state.name.isBlank() || state.lastName.isBlank() || state.secondName.isBlank() || state.email.isBlank() || state.number.isBlank()) {
                        error = "Todos los campos son obligatorios"
                    } else if (!isValidEmail(state.email)) {
                        error = "Correo inválido"
                    } else if (!isValidPhone(state.number)) {
                        error = "Número telefónico inválido"
                    } else {
                        error = ""
                        val EditedContact = ContactModel(
                            id = contactId,
                            name = state.name,
                            lastName = state.lastName,
                            secondName = state.secondName,
                            email = state.email,
                            number = state.number
                        )
                        directoryVM.updateContact(EditedContact)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editar" )
            }
        }
    }
}
