package com.example.workwork.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workwork.databinding.ActivityMainBinding
import com.example.workwork.db.TodoDataBase
import com.example.workwork.db.model.TodoItem
import com.example.workwork.db.repository.TodoRepository
import com.example.workwork.ui.adapters.AddDialogListener
import com.example.workwork.ui.adapters.ToDoItemDialog
import com.example.workwork.ui.adapters.TodoAdapter
import com.example.workwork.ui.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodoActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        setUpRecyclerView()

        todoViewModel.getAllItem().observe(this, Observer {list->
            adapter.submitList(ArrayList(list.map{it.copy()}))
        })

        activityMainBinding.addTodoBtn.setOnClickListener {
//            ToDoItemDialog(this, object : AddDialogListener {
//                override fun onAddButtonClick(item: TodoItem) {
//                    todoViewModel.upsert(item)
//                }
//            }).show()
            intent = Intent(applicationContext, AddTodoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setUpRecyclerView() {

        activityMainBinding.apply {
            adapter = TodoAdapter(viewModel = todoViewModel)
        }

        activityMainBinding.itemRv.layoutManager = LinearLayoutManager(this)
        activityMainBinding.itemRv.adapter = adapter
    }

}