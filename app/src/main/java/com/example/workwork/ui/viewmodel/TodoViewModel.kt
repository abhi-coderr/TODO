package com.example.workwork.ui.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workwork.db.model.TodoItem
import com.example.workwork.db.repository.TodoRepository
import com.example.workwork.ui.adapters.TodoAdapter
import com.example.workwork.ui.adapters.TodoCompleteAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class TodoViewModel @Inject constructor(private var todoRepository: TodoRepository) :
    ViewModel() {

    var todoCompleteAdapter = TodoCompleteAdapter()

    var todoAdapter = TodoAdapter(onEvent = { event ->
        when (event) {
            is TodoAdapter.MySealClass.OnSubmit -> {

                Log.d("this is the loggg","this->>>>>>>>${currentItem().toString()}")
                todoCompleteAdapter.submitList(ArrayList(currentItem().map { it.copy() }))

//                todoCompleteAdapter.submitList(ArrayList(todoCompleteAdapter.currentList.map { it.copy() }))
            }
            is TodoAdapter.MySealClass.OnDelete -> {
                delete(event.item)
            }
        }
    })

    private fun currentItem(): MutableList<TodoItem> {
        return todoAdapter.currentList
    }

    fun upsert(item: TodoItem) = viewModelScope.launch {
        todoRepository.upsert(item)
    }

    fun delete(item: TodoItem) = viewModelScope.launch {
        todoRepository.delete(item)
    }

    fun getAllItem() = todoRepository.getAllItems()


}