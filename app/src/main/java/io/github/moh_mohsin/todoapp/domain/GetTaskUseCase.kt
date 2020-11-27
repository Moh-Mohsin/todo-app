package io.github.moh_mohsin.todoapp.domain

import io.github.moh_mohsin.todoapp.data.Result
import io.github.moh_mohsin.todoapp.data.model.Task
import io.github.moh_mohsin.todoapp.data.repository.TaskRepository


class GetTaskUseCase(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(id: Int): Result<Task> {
            return taskRepository.getTask(id)
    }
}