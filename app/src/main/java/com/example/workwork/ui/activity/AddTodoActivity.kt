package com.example.workwork.ui.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.example.workwork.R
import com.example.workwork.databinding.ActivityAddTodoBinding
import com.example.workwork.db.model.TodoItem
import com.example.workwork.ui.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddTodoActivity : AppCompatActivity() {

    private lateinit var activityAddTodoBinding: ActivityAddTodoBinding

    private lateinit var viewModel: TodoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddTodoBinding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(activityAddTodoBinding.root)

        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        activityAddTodoBinding.dateSelector.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    activityAddTodoBinding.dateSelector.text = dat
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        activityAddTodoBinding.timeSelector.setOnClickListener {

            val mTimePicker: TimePickerDialog
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)

            mTimePicker = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    activityAddTodoBinding.timeSelector.text = String.format(
                        "%d : %d",
                        hourOfDay,
                        minute
                    )
                }, hour, minute, false
            )

            mTimePicker.show()

        }

        activityAddTodoBinding.addNewTodoBtn.setOnClickListener {
            val title = activityAddTodoBinding.titleData.text.toString()
            val date = activityAddTodoBinding.dateSelector.text.toString()
            val time = activityAddTodoBinding.timeSelector.text.toString()

            if (title.isNullOrEmpty()) {
                Toast.makeText(this, "Kindly put title", Toast.LENGTH_SHORT).show()
            } else {
                if (date.contentEquals("Select Due Date") && time.contentEquals("Select Due time")) {
                    val item = TodoItem(name = title, dueDate = "date", dueTime = "time")
                    viewModel.upsert(item = item)
                    Toast.makeText(this, "new task added", Toast.LENGTH_SHORT).show()
                    this.onBackPressed()
                } else {
                    val item = TodoItem(name = title, dueDate = date, dueTime = time)
                    viewModel.upsert(item = item)
                    Toast.makeText(this, "new task added", Toast.LENGTH_SHORT).show()
                    this.onBackPressed()
                }

            }
        }


    }
}