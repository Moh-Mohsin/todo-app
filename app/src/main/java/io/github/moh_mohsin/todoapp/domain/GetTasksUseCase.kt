package io.github.moh_mohsin.todoapp.domain

import io.github.moh_mohsin.todoapp.data.Result
import io.github.moh_mohsin.todoapp.data.model.Task
import io.github.moh_mohsin.todoapp.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow


class GetTasksUseCase(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(): Result<Flow<List<Task>>> {
            return taskRepository.getTasks()
    }
}