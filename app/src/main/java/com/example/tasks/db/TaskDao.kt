package com.example.tasks.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task:Task)

//    @Insert
//    fun insertAll(tasks:List<Task>)

    @Update
    suspend fun update(task:Task)

    @Delete
    suspend fun delete(task:Task)

    @Query("SELECT*FROM task_table ORDER BY taskId DESC ")
    fun getAll():LiveData<List<Task>>

    @Query("SELECT*FROM task_table WHERE taskId= :taskId ")
    fun getTask(taskId:Long):LiveData<Task>

}