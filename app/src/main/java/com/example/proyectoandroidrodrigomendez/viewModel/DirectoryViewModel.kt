package com.example.proyectoandroidrodrigomendez.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoandroidrodrigomendez.dataStore.ContactState
import com.example.proyectoandroidrodrigomendez.model.ContactModel
import com.example.proyectoandroidrodrigomendez.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectoryViewModel @Inject constructor(
    internal val repository: Repository
) : ViewModel() {

    private val _state = mutableStateOf(ContactState())
    val state = _state

    private val _contactsList = MutableStateFlow<List<ContactModel>>(emptyList())
    val contactsList: StateFlow<List<ContactModel>> = _contactsList

    init {
        loadContactos()
    }

    private fun loadContactos() {
        repository.getAllContacts()
            .onEach { list ->
                _contactsList.value = list
            }
            .launchIn(viewModelScope)
    }

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun onLastNameChange(lastName: String) {
        _state.value = _state.value.copy(lastName = lastName)
    }

    fun onSecondNameChange(secondName: String) {
        _state.value = _state.value.copy(secondName = secondName)
    }

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onNumberChange(number: String) {
        _state.value = _state.value.copy(number = number)
    }


    fun saveContact(name: String, lastName: String, secondName: String, email: String , number: String ) {
        val contact = ContactModel(
            name = name.trim(),
            lastName = lastName.trim(),
            secondName = secondName.trim(),
            email = email.trim(),
            number = number.trim()
        )
        viewModelScope.launch {
            repository.addContact(contact)
            clearFields()
        }
    }

    fun clearFields() {
        _state.value = ContactState()
    }

    fun deleteContact(contact: ContactModel) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }

    fun updateContact(contact: ContactModel) {
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }


}