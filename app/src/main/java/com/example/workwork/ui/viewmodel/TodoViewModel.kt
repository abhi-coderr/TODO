package com.example.workwork.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workwork.db.model.TodoItem
import com.example.workwork.db.repository.TodoRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoViewModel : ViewModel() {

    @Inject
    lateinit var todoRepository: TodoRepository

    fun upsert(item: TodoItem) = viewModelScope.launch {
        todoRepository.upsert(item)
    }

    fun delete(item: TodoItem) = viewModelScope.launch {
        todoRepository.delete(item)
    }

    fun getAllItem() = todoRepository.getAllItems()

}