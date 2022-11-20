package com.example.tasks.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.db.TaskDao

class EditTestViewModelFactory(val taskId: Long, val dao:TaskDao):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EditTaskViewModel::class.java)){
            return EditTaskViewModel(taskId,dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}