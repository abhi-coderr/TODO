package com.example.workwork.db.repository

import androidx.lifecycle.LiveData
import com.example.workwork.db.TodoDataBase
import com.example.workwork.db.model.TodoItem

class TodoRepository(
    private val db: TodoDataBase
) {
    suspend fun upsert(item: TodoItem) = db.getTodoDao().upsert(item)

    suspend fun delete(item: TodoItem) = db.getTodoDao().delete(item)

    fun getAllItems() = db.getTodoDao().getAllTodoItems()



}