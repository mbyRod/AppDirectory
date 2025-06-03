package com.example.proyectoandroidrodrigomendez.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "lastName")
    val lastName: String,

    @ColumnInfo(name = "secondName")
    val secondName: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "number")
    val number: String
)
