package com.example.workwork.ui.adapters

import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.workwork.databinding.TodoItemRvBinding
import com.example.workwork.db.model.TodoItem
import com.example.workwork.ui.viewmodel.TodoViewModel

class TodoAdapter(
    val onEvent: (MySealClass) -> Unit
) : ListAdapter<TodoItem, TodoAdapter.RecyclerViewHolder>(DiffUtilCallbackClass()) {

    sealed class MySealClass {
        data class OnSubmit(val item: TodoItem): MySealClass()
        data class OnDelete(val item: TodoItem): MySealClass()
    }

    inner class RecyclerViewHolder(private var binding: TodoItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoItem) {

            binding.apply {
//                val name = "<strike><font color=\'#757575\'>${item.name}</font></strike>"
                tvName.text = item.name
                taskCompleteBtn.isChecked = item.isChecked == true
                tvAmount.text = item.amount.toString()
                dateIndicator.text = item.dueDate.toString()
                timeIndicator.text = item.dueTime.toString()

                ivDelete.setOnClickListener {
                    onEvent(MySealClass.OnDelete(item))
                }

                taskCompleteBtn.setOnCheckedChangeListener { compoundButton, b ->
                    onEvent(MySealClass.OnSubmit(item))
                }

            }
        }
    }

    class DiffUtilCallbackClass : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: TodoItem, newItem: TodoItem): Any? {

            if (oldItem != newItem) {
                return newItem
            }

            return super.getChangePayload(oldItem, newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            TodoItemRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val newItem = payloads[0] as TodoItem
            holder.bind(newItem)
        }
    }
}