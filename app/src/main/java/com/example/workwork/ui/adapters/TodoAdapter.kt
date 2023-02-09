package com.example.workwork.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.workwork.databinding.TodoItemRvBinding
import com.example.workwork.db.model.TodoItem
import com.example.workwork.ui.viewmodel.TodoViewModel

class TodoAdapter(
    val onEventMinus: (MySealClass) -> Unit,
    val onEventDelete: (MySealClass) -> Unit,
    val onEventPlus: (MySealClass) -> Unit, val viewModel: TodoViewModel
) : ListAdapter<TodoItem, TodoAdapter.RecyclerViewHolder>(DiffUtilCallbackClass()) {

    sealed class MySealClass {
        data class OnMinusClick(val item: TodoItem) : MySealClass()
        data class OnPlusClick(val item: TodoItem) : MySealClass()
        data class OnDelete(val item: TodoItem) : MySealClass()
    }

    inner class RecyclerViewHolder(private var binding: TodoItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoItem) {
            binding.apply {
                tvName.text = item.name
                tvAmount.text = "${item.amount}"
                ivMinus.setOnClickListener {
                    item.amount++
                    viewModel.upsert(item)
                }
                ivDelete.setOnClickListener {
                    viewModel.delete(item)
                }
                ivPlus.setOnClickListener {
                    item.amount++
                    viewModel.upsert(item)
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