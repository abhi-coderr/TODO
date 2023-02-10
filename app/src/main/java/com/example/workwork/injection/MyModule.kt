package com.example.workwork.injection

import android.content.Context
import com.example.workwork.db.TodoDataBase
import com.example.workwork.db.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyModule {

    @Provides
    @Singleton
    fun providerDataBase(@ApplicationContext context: Context): TodoDataBase{
        return TodoDataBase(context)
    }

    @Provides
    @Singleton
    fun provideRepository(dataBase: TodoDataBase): TodoRepository{
        return TodoRepository(dataBase)
    }


}