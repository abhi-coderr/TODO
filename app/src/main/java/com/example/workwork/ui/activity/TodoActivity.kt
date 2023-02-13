package com.example.workwork.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workwork.databinding.ActivityMainBinding
import com.example.workwork.ui.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        activityMainBinding.todoViewModel = todoViewModel

        setUpRecyclerView()

        todoViewModel.getAllItem().observe(this, Observer { list ->
            todoViewModel.todoAdapter.submitList(ArrayList(list.map { it.copy() }))
        })

//        todoViewModel.getAllItem().observe(this, Observer {list->
//            todoViewModel.todoCompleteAdapter.submitList(ArrayList(list.map { it.copy() }))
//
//        })

        activityMainBinding.addTodoBtn.setOnClickListener {
            intent = Intent(applicationContext, AddTodoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setUpRecyclerView() {

        activityMainBinding.apply {

            activityMainBinding.itemRv.layoutManager = LinearLayoutManager(this@TodoActivity)

            completedItemsRecyclerview.layoutManager = LinearLayoutManager(this@TodoActivity)
        }

    }

}