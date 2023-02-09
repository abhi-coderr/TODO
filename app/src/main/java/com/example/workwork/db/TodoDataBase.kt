package com.example.workwork.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workwork.db.model.TodoItem

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoDataBase : RoomDatabase() {

    abstract fun getTodoDao(): TodoDao

    companion object {
            //create one instance of this database class
            @Volatile
            private var instance: TodoDataBase? = null
            //now after this create invoke function, so that whenever constructor will create like "TodoDatabase()"
            //this function will run
            private val LOCK = Any()
            operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
                instance ?: createDatabase(context).also { instance = it }
            }

            private fun createDatabase(context: Context) =
                Room.databaseBuilder(
                    context.applicationContext,
                    TodoDataBase::class.java,
                    name = "TodoDB.db"
                ).build()
    }

}