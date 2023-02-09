package com.example.workwork.injection

import android.content.Context
import com.example.workwork.db.TodoDataBase
import com.example.workwork.db.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyModule {

    @Provides
    @Singleton
    fun provideRepository(dataBase: TodoDataBase): TodoRepository{
        return TodoRepository(dataBase)
    }

    @Provides
    @Singleton
    fun providerDataBase(context: Context): TodoDataBase{
        return TodoDataBase.invoke(context)
    }

}