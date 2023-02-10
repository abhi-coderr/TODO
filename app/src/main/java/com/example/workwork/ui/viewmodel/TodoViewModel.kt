package com.example.workwork.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workwork.db.model.TodoItem
import com.example.workwork.db.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private var todoRepository: TodoRepository) : ViewModel() {

    fun upsert(item: TodoItem) = viewModelScope.launch {
        todoRepository.upsert(item)
    }

    fun delete(item: TodoItem) = viewModelScope.launch {
        todoRepository.delete(item)
    }

    fun getAllItem() = todoRepository.getAllItems()

}