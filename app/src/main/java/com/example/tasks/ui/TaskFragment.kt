package com.example.tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasks.R
import com.example.tasks.databinding.FragmentTaskBinding
import com.example.tasks.db.TaskDatabase

class TaskFragment : Fragment() {

    private var _binding :FragmentTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater,container,false)

        //val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(requireContext()).taskDao
        val viewModelFactory = TaskViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory)[TaskViewModel::class.java]

        viewModel.navigateToTask.observe(viewLifecycleOwner){
            it?.let {
                val bundle =Bundle()
                bundle.putLong("taskId",it)
                this.findNavController().navigate(R.id.action_taskFragment_to_editTaskFragment,bundle)
                viewModel.onTaskNavigated()
            }
        }

        val adapter = TaskItemAdapter{taskId ->
            Toast.makeText(binding.root.context,"Clicked $taskId", Toast.LENGTH_SHORT).show()
//            val bundle = Bundle()
//            bundle.putLong("taskId",taskId)
//            this.findNavController().navigate(R.id.action_taskFragment_to_editTaskFragment,bundle)
            viewModel.onTaskClicked(taskId)
        }
        binding.recyclerView.adapter = adapter

        binding.saveButton.setOnClickListener {
            viewModel.addTask(binding.taskName.text.toString())
            //updateScreen
        }

        viewModel.tasks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}