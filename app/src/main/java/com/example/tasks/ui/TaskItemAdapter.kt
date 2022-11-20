package com.example.tasks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.databinding.TaskItemBinding
import com.example.tasks.db.Task

class TaskItemAdapter(private val clickListener:(taskId:Long)->Unit): ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallBack()) {


    inner class TaskItemViewHolder(private val binding:TaskItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(task:Task,clickListener: (taskId: Long) -> Unit){
            binding.root.setOnClickListener {
                clickListener(task.taskId)
                //Toast.makeText(binding.root.context,"Clicked ${task.taskId}",Toast.LENGTH_SHORT).show()
            }
            binding.taskName.text = task.taskName
            binding.taskDone.isChecked = task.taskDone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        return TaskItemViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.onBind(getItem(position),clickListener)
    }


}