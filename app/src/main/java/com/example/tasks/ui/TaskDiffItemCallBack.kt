package com.example.tasks.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.tasks.db.Task

class TaskDiffItemCallBack: DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = (oldItem.taskId == newItem.taskId)

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = (oldItem == newItem)
}