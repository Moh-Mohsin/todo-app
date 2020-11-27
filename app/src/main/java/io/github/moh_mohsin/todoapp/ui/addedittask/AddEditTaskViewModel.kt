package io.github.moh_mohsin.todoapp.ui.addedittask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.moh_mohsin.todoapp.data.AppException
import io.github.moh_mohsin.todoapp.data.Message
import io.github.moh_mohsin.todoapp.data.Result
import io.github.moh_mohsin.todoapp.data.toErrorMessage
import io.github.moh_mohsin.todoapp.di.KodeinInjector
import io.github.moh_mohsin.todoapp.domain.AddTaskUseCase
import io.github.moh_mohsin.todoapp.ui.addedittask.AddEditTaskState.*
import io.github.moh_mohsin.todoapp.util.Event
import io.github.moh_mohsin.todoapp.util.toLive
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance

class AddEditTaskViewModel : ViewModel() {

    private val addTaskUseCase by KodeinInjector.instance<AddTaskUseCase>()

    private val _state = MutableLiveData<AddEditTaskState>()
    val state = _state.toLive()

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            _state.value = Loading
            _state.value = when (val result = addTaskUseCase(title, description)) {
                is Result.Success -> Success
                is Result.Error -> Error(result.toErrorMessage())
            }
        }
    }
}

sealed class AddEditTaskState {
    object Loading : AddEditTaskState()
    object Success : AddEditTaskState()
    data class Error(val message: Message) : AddEditTaskState()
}