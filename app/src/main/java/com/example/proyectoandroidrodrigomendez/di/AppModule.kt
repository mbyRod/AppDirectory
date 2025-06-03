package com.example.proyectoandroidrodrigomendez.di

import android.content.Context
import androidx.room.Room
import com.example.proyectoandroidrodrigomendez.room.DBDirectory
import com.example.proyectoandroidrodrigomendez.room.DBDirectoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providerDirectoryDao(directorioDatabase: DBDirectory): DBDirectoryDao {
        return directorioDatabase.directoryDao()
    }

    @Singleton
    @Provides
    fun providerDirectoryDatabase(@ApplicationContext context: Context): DBDirectory {
        return Room.databaseBuilder(
            context,
            DBDirectory::class.java,
            "directory_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}