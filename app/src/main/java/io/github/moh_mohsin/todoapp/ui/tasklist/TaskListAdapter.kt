package io.github.moh_mohsin.todoapp.ui.tasklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.moh_mohsin.todoapp.data.model.Task
import io.github.moh_mohsin.todoapp.databinding.SingleTaskBinding

class TaskListAdapter(
    val onClick: (task: Task) -> Unit
) :
    ListAdapter<Task, TaskListAdapter.ViewHolder>(ResponseDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)

        holder.bind(onClick, todo!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: SingleTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            onClick: (task: Task) -> Unit,
            task: Task
        ) {
            binding.title.text = task.title
            binding.description.text = task.description
            binding.root.setOnClickListener {
                onClick.invoke(task)
            }

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SingleTaskBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class ResponseDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldTask: Task, newTask: Task): Boolean {
        return oldTask.id == newTask.id
    }

    override fun areContentsTheSame(oldTask: Task, newTask: Task): Boolean {
        return oldTask == newTask

    }
}




