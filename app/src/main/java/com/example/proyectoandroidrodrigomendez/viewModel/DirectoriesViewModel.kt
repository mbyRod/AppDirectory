package com.example.proyectoandroidrodrigomendez.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoandroidrodrigomendez.model.ContactModel
import com.example.proyectoandroidrodrigomendez.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DirectoriesViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _contactsList = MutableStateFlow<List<ContactModel>>(emptyList())
    val contactList = _contactsList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllContacts().collect { list ->
                _contactsList.value = list ?: emptyList()
            }
        }
    }

    fun addContact(contact: ContactModel) = viewModelScope.launch { repository.addContact(contact) }

    fun updateContact(contact: ContactModel) = viewModelScope.launch { repository.updateContact(contact) }

    fun deleteContact(contact: ContactModel) = viewModelScope.launch { repository.deleteContact(contact) }
}
