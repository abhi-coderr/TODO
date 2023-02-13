package com.example.workwork.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_item")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_is_checked")
    var isChecked: Boolean? = null,
    @ColumnInfo(name = "item_amount")
    var amount: Int? = null,
    @ColumnInfo(name = "item_due_date")
    var dueDate: String? = null,
    @ColumnInfo(name = "item_due_time")
    var dueTime: String? = null
)