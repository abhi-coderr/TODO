package com.example.workwork.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.workwork.db.model.TodoItem

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: TodoItem)

    @Delete
    suspend fun delete(item: TodoItem)

    @Query(value = "SELECT * FROM todo_item")
    fun getAllTodoItems(): LiveData<List<TodoItem>>

}