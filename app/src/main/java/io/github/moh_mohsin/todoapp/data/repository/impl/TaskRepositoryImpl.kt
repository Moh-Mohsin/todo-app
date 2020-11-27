package io.github.moh_mohsin.todoapp.data.repository.impl

import io.github.moh_mohsin.todoapp.data.Result
import io.github.moh_mohsin.todoapp.data.model.Task
import io.github.moh_mohsin.todoapp.data.repository.TaskRepository
import io.github.moh_mohsin.todoapp.data.source.TaskDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val taskDataSource: TaskDataSource) : TaskRepository {
    override fun getTasks(): Result<Flow<List<Task>>> {
        return taskDataSource.getTasks()
    }

    override suspend fun addTask(task: Task): Result<Task> {
        delay(1500)
        return taskDataSource.addTask(task)
    }

    override suspend fun getTask(id: Int): Result<Task> {
        delay(1500)
        return taskDataSource.addTask(id)
    }
}