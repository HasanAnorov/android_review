package com.example.tasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.db.Task
import com.example.tasks.db.TaskDao
import kotlinx.coroutines.launch

class EditTaskViewModel(val taskId:Long,val dao: TaskDao):ViewModel(){

    val task = dao.getTask(taskId)


    private val _navigateToTaskFragment = MutableLiveData<Boolean>()
    val navigateToTaskFragment: LiveData<Boolean> get() = _navigateToTaskFragment

    fun updateTask(taskName:String,checked:Boolean){
        task.value?.taskDone = checked
        task.value?.taskName = taskName
        viewModelScope.launch{
            dao.update(task.value!!)
            _navigateToTaskFragment.value = true
        }
    }

    fun deleteTask(){
        viewModelScope.launch {
            dao.delete(task.value!!)
            _navigateToTaskFragment.value = true
        }
    }

    fun onNavigatedToTaskFragment(){
        _navigateToTaskFragment.value = false
    }

}