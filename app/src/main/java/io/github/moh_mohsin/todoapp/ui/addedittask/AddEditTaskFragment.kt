package io.github.moh_mohsin.todoapp.ui.addedittask

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.github.moh_mohsin.todoapp.R
import io.github.moh_mohsin.todoapp.data.get
import io.github.moh_mohsin.todoapp.databinding.AddEditTaskFragmentBinding
import io.github.moh_mohsin.todoapp.util.*

class AddEditTaskFragment : Fragment(R.layout.add_edit_task_fragment) {

    private val binding by viewBinding(AddEditTaskFragmentBinding::bind)
    private val viewModel by lazy { ViewModelProvider(this).get(AddEditTaskViewModel::class.java) }

    private var errorSnack: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                AddEditTaskState.Loading -> {
                    binding.loading.show()
                    binding.container.hide()
                }
                AddEditTaskState.Success -> {
                    toast(R.string.item_added)
                    findNavController().popBackStack()
                }
                is AddEditTaskState.Error -> {
                    binding.loading.hide()
                    binding.container.show()
                    errorSnack = Snackbar.make(
                        binding.root,
                        state.message.get(requireContext()),
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction(R.string.try_again) {
                        addTask()
                        errorSnack!!.dismiss()
                    }
                    errorSnack!!.show()
                }
            }

        }
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_task_options, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_item -> {
                addTask()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addTask() {
        val title = binding.titleEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()
        if (title.isBlank() || description.isBlank()) {
            toast(R.string.error_title_description_required)
        } else {
            viewModel.addTask(title, description)
        }
    }

}