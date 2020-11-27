package io.github.moh_mohsin.todoapp.ui.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.github.moh_mohsin.todoapp.R
import io.github.moh_mohsin.todoapp.databinding.TaskDetailFragmentBinding
import io.github.moh_mohsin.todoapp.util.viewBinding

class TaskDetailFragment : Fragment(R.layout.task_detail_fragment) {

    private val binding by viewBinding(TaskDetailFragmentBinding::bind)

    private val task by lazy { TaskDetailFragmentArgs.fromBundle(requireArguments()).task }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.title.text = todo.title
        requireActivity().title = task.title
        binding.description.text = task.description
    }

}