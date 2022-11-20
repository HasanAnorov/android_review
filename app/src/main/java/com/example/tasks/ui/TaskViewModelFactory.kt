package com.example.tasks.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.db.Task
import com.example.tasks.db.TaskDao

class TaskViewModelFactory(private val dao:TaskDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)){
            return TaskViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }

}