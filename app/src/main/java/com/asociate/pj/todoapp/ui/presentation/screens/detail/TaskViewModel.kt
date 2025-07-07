package com.asociate.pj.todoapp.ui.presentation.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asociate.pj.todoapp.ui.domain.Task
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.UUID

class TaskViewModel : ViewModel() {

    private val fakeTaskLocalDataSource = com.asociate.pj.todoapp.ui.data.FakeTaskLocalDataSource

    var state by mutableStateOf(TaskScreenState())
        private set

    private val eventChannel = Channel<TaskEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(actionTask: ActionTask) {
        viewModelScope.launch {
            when (actionTask) {
                is ActionTask.ChangeTaskCategory -> {
                    state = state.copy(category = actionTask.category)
                }

                is ActionTask.ChangeTaskDone -> {
                    state = state.copy(isTaskDone = actionTask.isTaskDone)
                }

                ActionTask.SaveTask -> {
                    val task = Task(
                        id = UUID.randomUUID().toString(),
                        title = state.taskName,
                        description = state.taskDescription,
                        category = state.category,
                        isCompleted = state.isTaskDone
                    )
                    fakeTaskLocalDataSource.addTask(task)
                    eventChannel.send(TaskEvent.TaskCreated)
                }

                else -> Unit
            }
        }
    }
}