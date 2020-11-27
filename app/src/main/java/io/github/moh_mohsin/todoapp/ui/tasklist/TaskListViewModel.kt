package io.github.moh_mohsin.todoapp.ui.tasklist

import androidx.lifecycle.*
import io.github.moh_mohsin.todoapp.data.AppException
import io.github.moh_mohsin.todoapp.data.Message
import io.github.moh_mohsin.todoapp.data.Result
import io.github.moh_mohsin.todoapp.data.model.Task
import io.github.moh_mohsin.todoapp.data.toErrorMessage
import io.github.moh_mohsin.todoapp.di.KodeinInjector
import io.github.moh_mohsin.todoapp.domain.GetTasksUseCase
import io.github.moh_mohsin.todoapp.ui.addedittask.AddEditTaskState
import io.github.moh_mohsin.todoapp.ui.tasklist.TaskListState.*
import io.github.moh_mohsin.todoapp.util.toLive
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance
import kotlin.Error


class TaskListViewModel : ViewModel() {

    private val getTasksUseCase by KodeinInjector.instance<GetTasksUseCase>()

    private val _state = MutableLiveData<TaskListState>()
    val state = _state.toLive()


    init {
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            _state.value = Loading
            _state.value = when (val result = getTasksUseCase()) {
                is Result.Success -> Success(result.data.asLiveData())
                is Result.Error -> Error(result.toErrorMessage())
            }
        }
    }

}

sealed class TaskListState {
    class Success(val taskList: LiveData<List<Task>>) : TaskListState()
    class Error(val message: Message) : TaskListState()
    object Loading : TaskListState()
}