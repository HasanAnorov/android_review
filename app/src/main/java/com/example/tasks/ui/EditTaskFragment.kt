package com.example.tasks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasks.R
import com.example.tasks.databinding.FragmentEditTaskBinding
import com.example.tasks.databinding.FragmentTaskBinding
import com.example.tasks.db.TaskDatabase

class EditTaskFragment : Fragment() {

    private val TAG = "EDIT_TASK"

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTaskBinding.inflate(inflater,container,false)

        val taskId = arguments?.getLong("taskId")?:0
        Log.d(TAG,"$taskId - taskId")

        val dao = TaskDatabase.getInstance(requireContext()).taskDao
        val viewModelFactory = EditTestViewModelFactory(taskId,dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EditTaskViewModel::class.java)

        viewModel.navigateToTaskFragment.observe(viewLifecycleOwner){ navigate ->
            if(navigate){
                this.findNavController().navigate(R.id.action_editTaskFragment_to_taskFragment)
                viewModel.onNavigatedToTaskFragment()
            }
        }

        viewModel.task.observe(viewLifecycleOwner) {
            Log.d("EDIT_TASK","${it.taskName} + ${it.taskDone}")
            binding.taskDone.isChecked = it.taskDone
            binding.taskName.setText(it.taskName.toString())
        }

        binding.updateButton.setOnClickListener {
            viewModel.updateTask(binding.taskName.text.toString(),binding.taskDone.isChecked)
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteTask()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}