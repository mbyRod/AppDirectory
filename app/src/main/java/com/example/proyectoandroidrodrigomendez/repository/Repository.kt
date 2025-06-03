package com.example.proyectoandroidrodrigomendez.repository

import com.example.proyectoandroidrodrigomendez.model.ContactModel
import com.example.proyectoandroidrodrigomendez.room.DBDirectoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val directorioDao: DBDirectoryDao) {

    suspend fun addContact(contact: ContactModel) = directorioDao.insert(contact)

    suspend fun updateContact(contact: ContactModel) = directorioDao.update(contact)

    suspend fun deleteContact(contact: ContactModel) = directorioDao.delete(contact)

    fun getAllContacts(): Flow<List<ContactModel>> =
        directorioDao.getContacts().flowOn(Dispatchers.IO).conflate()

    fun getContactById(id: Long): Flow<ContactModel> =
        directorioDao.getContactById(id).flowOn(Dispatchers.IO).conflate()
}