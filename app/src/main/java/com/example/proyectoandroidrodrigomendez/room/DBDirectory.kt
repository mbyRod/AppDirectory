package com.example.proyectoandroidrodrigomendez.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectoandroidrodrigomendez.model.ContactModel

@Database(entities = [ContactModel::class], version = 1, exportSchema = false)
abstract class DBDirectory : RoomDatabase() {
    abstract fun directoryDao(): DBDirectoryDao
}