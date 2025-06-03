package com.example.proyectoandroidrodrigomendez.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoandroidrodrigomendez.components.Form
import com.example.proyectoandroidrodrigomendez.components.TopBar
import com.example.proyectoandroidrodrigomendez.viewModel.DirectoryViewModel


@Composable
fun AddContactView(navController: NavController, directoryVM: DirectoryViewModel) {
    Scaffold (
        topBar = { TopBar("Añadir Contacto",
            onNavClick = {navController.popBackStack()}
            ) }
    )
    {
         innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Form(isEdit = false) { name, lastName, secondName, email, number ->
                    directoryVM.saveContact( name, lastName, secondName, email, number)
                    navController.popBackStack()
                }
            }
    }
}