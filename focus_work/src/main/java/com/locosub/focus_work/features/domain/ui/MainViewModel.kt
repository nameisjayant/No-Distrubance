package com.locosub.focus_work.features.domain.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locosub.focus_work.common.doOnFailure
import com.locosub.focus_work.common.doOnLoading
import com.locosub.focus_work.common.doOnSuccess
import com.locosub.focus_work.data.models.Task
import com.locosub.focus_work.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _tasksResponse: MutableState<TaskStates> = mutableStateOf(TaskStates())
    val taskResponse: State<TaskStates> = _tasksResponse

    private val _addTaskEventFlow = MutableSharedFlow<TaskUiEvent<String>>()
    val addTaskEventFlow = _addTaskEventFlow.asSharedFlow()

    private val _addTaskDeleteEventFlow = MutableSharedFlow<TaskUiEvent<String>>()
    val addTaskDeleteEventFlow = _addTaskDeleteEventFlow.asSharedFlow()

    private val _addTaskUpdateEventFlow = MutableSharedFlow<TaskUiEvent<String>>()
    val addTaskUpdateEventFlow = _addTaskUpdateEventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.getTask()
                .doOnSuccess {
                    _tasksResponse.value = TaskStates(
                        data = it
                    )
                }
                .doOnFailure {
                    _tasksResponse.value = TaskStates(
                        msg = it?.message ?: "Something went wrong"
                    )
                }
                .doOnLoading {
                    _tasksResponse.value = TaskStates(
                        isLoading = true
                    )
                }
                .collect()
        }
    }

    fun onEvent(taskEvents: TaskEvents){
        when(taskEvents){
            is TaskEvents.AddTask -> {
               viewModelScope.launch {
                   repository.addTask(taskEvents.data)
                       .doOnSuccess {
                           _addTaskEventFlow.emit(TaskUiEvent.Success(it))
                       }
                       .doOnFailure {
                           _addTaskEventFlow.emit(TaskUiEvent.Failure(it ?: Throwable()))
                       }
                       .doOnLoading {
                           _addTaskEventFlow.emit(TaskUiEvent.Loading)
                       }
                       .collect()
               }
            }
            is TaskEvents.DeleteTask -> {
                viewModelScope.launch {
                    repository.deleteTask(taskEvents.key)
                        .doOnSuccess {
                            _addTaskDeleteEventFlow.emit(TaskUiEvent.Success(it))
                        }
                        .doOnFailure {
                            _addTaskDeleteEventFlow.emit(TaskUiEvent.Failure(it ?: Throwable()))
                        }
                        .doOnLoading {
                            _addTaskDeleteEventFlow.emit(TaskUiEvent.Loading)
                        }
                        .collect()
                }
            }
            is TaskEvents.UpdateTask -> {
                viewModelScope.launch {
                    repository.updateTask(taskEvents.data)
                        .doOnSuccess {
                            _addTaskUpdateEventFlow.emit(TaskUiEvent.Success(it))
                        }
                        .doOnFailure {
                            _addTaskUpdateEventFlow.emit(TaskUiEvent.Failure(it ?: Throwable()))
                        }
                        .doOnLoading {
                            _addTaskUpdateEventFlow.emit(TaskUiEvent.Loading)
                        }
                        .collect()
                }
            }
        }
    }


}

sealed class TaskUiEvent<out T> {

    data class Success<T>(val data: T) : TaskUiEvent<T>()
    data class Failure(val msg: Throwable) : TaskUiEvent<Nothing>()
    object Loading : TaskUiEvent<Nothing>()

}

sealed class TaskEvents {
    data class AddTask(val data: Task) : TaskEvents()
    data class UpdateTask(val data: Task.TaskResponse) : TaskEvents()
    data class DeleteTask(val key: String) : TaskEvents()
}

data class TaskStates(
    val data: List<Task.TaskResponse> = emptyList(),
    val msg: String = "",
    val isLoading: Boolean = false
)