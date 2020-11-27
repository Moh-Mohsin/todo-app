package io.github.moh_mohsin.todoapp.data.repository

import io.github.moh_mohsin.todoapp.data.Result
import io.github.moh_mohsin.todoapp.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Result<Flow<List<Task>>>

    suspend fun addTask(task: Task): Result<Task>

    suspend fun getTask(id: Int): Result<Task>
}