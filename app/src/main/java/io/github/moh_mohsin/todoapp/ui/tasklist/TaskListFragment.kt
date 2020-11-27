package io.github.moh_mohsin.todoapp.ui.tasklist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.github.moh_mohsin.todoapp.R
import io.github.moh_mohsin.todoapp.data.get
import io.github.moh_mohsin.todoapp.databinding.TaskListFragmentBinding
import io.github.moh_mohsin.todoapp.util.showOrHide
import io.github.moh_mohsin.todoapp.util.toast
import io.github.moh_mohsin.todoapp.util.viewBinding

class TaskListFragment : Fragment(R.layout.task_list_fragment) {

    private val binding by viewBinding(TaskListFragmentBinding::bind)
    private val viewModel by lazy { ViewModelProvider(this).get(TaskListViewModel::class.java) }

    private var errorSnack: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            val direction = TaskListFragmentDirections.toAddTaskFragment()
            findNavController().navigate(direction)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getTasks()
        }

        val adapter = TaskListAdapter { item ->
            val direction = TaskListFragmentDirections.toTaskDetailFragment(item)
            findNavController().navigate(direction)
        }

        binding.itemsList.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state){
                is TaskListState.Success -> {
                    binding.swipeRefresh.isRefreshing = false
                    state.taskList.observe(viewLifecycleOwner){ tasks ->
                        binding.noTasksText.showOrHide(tasks.isEmpty())
                        toast("tasks ${tasks.size}")
                        adapter.submitList(tasks)
                    }
                }
                is TaskListState.Error -> {
                    binding.swipeRefresh.isRefreshing = false
                    errorSnack = Snackbar.make(
                        binding.root,
                        state.message.get(requireContext()),
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction(R.string.try_again) {
                        viewModel.getTasks()
                        errorSnack!!.dismiss()
                    }
                    errorSnack!!.show()
                }
                TaskListState.Loading -> {
                    binding.swipeRefresh.isRefreshing = true
                }
            }
        }
    }

}