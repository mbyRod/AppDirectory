package com.example.proyectoandroidrodrigomendez.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectoandroidrodrigomendez.model.ContactModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DBDirectoryDao {

    @Query("SELECT * FROM contact")
    fun getContacts(): Flow<List<ContactModel>>

    @Query("SELECT * FROM contact WHERE id = :id")
    fun getContactById(id: Long): Flow<ContactModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: ContactModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(contact: ContactModel)

    @Delete
    suspend fun delete(contact: ContactModel)
}