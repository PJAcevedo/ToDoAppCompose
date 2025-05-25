package com.asociate.pj.todoapp.ui.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asociate.pj.todoapp.ui.data.FakeTaskLocalDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeScreenViewModel : ViewModel() {
    private val taskLocalDataSource = FakeTaskLocalDataSource

    var state by mutableStateOf(HomeDataState())
        private set

    private val eventChannel = Channel<HomeScreenEvent>()
    val event = eventChannel.receiveAsFlow()

    init {

        state = state.copy(
            date = LocalDate.now().let {
                DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy").format(it)
            }
        )
        taskLocalDataSource.tasksFlow.onEach {
            val completedTask = it.filter { task -> task.isCompleted }
            val pendingTask = it.filter { task -> !task.isCompleted }

            state = state.copy(
                date = "March 20, 2023",
                summary = pendingTask.size.toString(),
                completedTask = completedTask,
                pendingTask = pendingTask
            )
        }.launchIn(viewModelScope)
    }

    fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.OnToggleTask -> {
                val updatedTask = action.task.copy(isCompleted = !action.task.isCompleted)
                viewModelScope.launch {
                    taskLocalDataSource.updateTask(updatedTask)
                }
            }
            is HomeScreenAction.OnDeleteTask -> {
                viewModelScope.launch {
                    taskLocalDataSource.removeTask(action.task)
                    eventChannel.send(HomeScreenEvent.DeletedTask)
                }
            }
            is HomeScreenAction.OnDeleteAllTasks -> {
                viewModelScope.launch {
                    taskLocalDataSource.deleteAllTasks()
                    eventChannel.send(HomeScreenEvent.DeleteAllTasks)
                }
            }
            else -> Unit
        }
    }

}