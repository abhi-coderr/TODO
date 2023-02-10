package com.example.workwork.ui.adapters

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.workwork.databinding.DialogItemTodoBinding
import com.example.workwork.db.model.TodoItem

class ToDoItemDialog(context: Context, var addDialogListener : AddDialogListener): AppCompatDialog(context) {

    private lateinit var dialogItemTodoBinding : DialogItemTodoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        dialogItemTodoBinding = DialogItemTodoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(dialogItemTodoBinding.root)

        dialogItemTodoBinding.tvAdd.setOnClickListener {
            val name = dialogItemTodoBinding.editName.text.toString()
            val amount = dialogItemTodoBinding.editAmount.text.toString()
            if (name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context, "kindly check all info", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = TodoItem(name = name, amount = amount.toInt(),)
            addDialogListener.onAddButtonClick(item)
            dismiss()
        }

        dialogItemTodoBinding.tvCancel.setOnClickListener {
            cancel()
        }


    }


}