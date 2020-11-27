package io.github.moh_mohsin.todoapp.domain

import io.github.moh_mohsin.todoapp.data.Result
import io.github.moh_mohsin.todoapp.data.model.Task
import io.github.moh_mohsin.todoapp.data.repository.TaskRepository


class AddTaskUseCase(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(title: String, description: String): Result<Task> {
        return taskRepository.addTask(Task(0, title, description))
    }
}