package io.github.moh_mohsin.todoapp.data.source.inmemory

import io.github.moh_mohsin.todoapp.data.NotFoundException
import io.github.moh_mohsin.todoapp.data.Result
import io.github.moh_mohsin.todoapp.data.model.Task
import io.github.moh_mohsin.todoapp.data.source.TaskDataSource
import io.github.moh_mohsin.todoapp.data.toError
import io.github.moh_mohsin.todoapp.data.toSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class InMemoryTaskDataSource : TaskDataSource {

    var nextId = 1
    val todoStore = hashMapOf<Int, Task>()

    private val _todosFlow = MutableStateFlow(listOf<Task>())

    init {
        todoStore[nextId] = Task(nextId++, "Todo app", "Push the first commit of the Todo App")
        todoStore[nextId] = Task(nextId++, "Room", "Use Room to store Todos")
        _todosFlow.value = todoStore.values.toList()
    }

    override fun getTasks(): Result<StateFlow<List<Task>>> {
        return _todosFlow.toSuccess()
    }

    override fun addTask(task: Task): Result<Task> {
        val todoToAdd = task.copy(id = nextId++)
        todoStore[todoToAdd.id] = task
        _todosFlow.value = todoStore.values.toList()
        return todoToAdd.toSuccess()
    }

    override fun addTask(id: Int): Result<Task> {
        return todoStore[id]?.toSuccess() ?: NotFoundException().toError()
    }
}