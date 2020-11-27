package io.github.moh_mohsin.todoapp.data.source

import io.github.moh_mohsin.todoapp.data.Result
import io.github.moh_mohsin.todoapp.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskDataSource {

    fun getTasks(): Result<Flow<List<Task>>>

    fun addTask(task: Task): Result<Task>

    fun addTask(id: Int): Result<Task>
}