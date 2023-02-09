package com.example.workwork.ui.adapters

import com.example.workwork.db.model.TodoItem

interface AddDialogListener {
    fun onAddButtonClick(item : TodoItem)
}