package com.example.tasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.db.Task
import com.example.tasks.db.TaskDao
import kotlinx.coroutines.launch

class TaskViewModel(private val dao:TaskDao):ViewModel() {

    val tasks = dao.getAll()

    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask :LiveData<Long?> get() = _navigateToTask

    fun onTaskClicked(taskId:Long){
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated(){
        _navigateToTask.value  = null
    }
//    val tasksString = Transformations.map(tasks) {
//        tasks -> formatTasks(tasks)
//    }

//    private fun formatTasks(tasks:List<Task>):String{
//        return tasks.fold(""){
//            str,item -> str + '\n' + formatTask(item)
//        }
    //}

//    private fun formatTask(task: Task):String{
//        var str = "ID: ${task.taskId}"
//        str += '\n' + "Name : ${task.taskName}"
//        str += '\n' + "Status: ${task.taskDone}" + '\n'
//        return str
//    }

    fun addTask(newTaskName:String){
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }
}