package com.asociate.pj.todoapp.ui.data

import com.asociate.pj.todoapp.ui.domain.Task
import com.asociate.pj.todoapp.ui.domain.TaskLocalDataSource
import com.asociate.pj.todoapp.ui.presentation.screens.home.providers.completedTask
import com.asociate.pj.todoapp.ui.presentation.screens.home.providers.pendingTask
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object FakeTaskLocalDataSource: TaskLocalDataSource {
    private val _tasksFlow = MutableStateFlow<List<Task>>(emptyList())

    init {
        _tasksFlow.value = completedTask + pendingTask
    }

    override val tasksFlow: Flow<List<Task>>
        get() = _tasksFlow

    override suspend fun addTask(task: Task) {
        val tasks = _tasksFlow.value.toMutableList()
        tasks.add(task)
        delay(1000L)
        _tasksFlow.value = tasks
    }

    override suspend fun updateTask(updatedTask: Task) {
        val tasks = _tasksFlow.value.toMutableList()
        val taskIndex = tasks.indexOfFirst { it.id == updatedTask.id }
        if (taskIndex != -1) {
            tasks[taskIndex] = updatedTask
            delay(1000L)
            _tasksFlow.value = tasks
        }
    }

    override suspend fun removeTask(task: Task) {
        val tasks = _tasksFlow.value.toMutableList()
        tasks.remove(task)
        delay(1000L)
        _tasksFlow.value = tasks
    }

    override suspend fun deleteAllTasks() {
        delay(1000L)
        _tasksFlow.value = emptyList()
    }

    override suspend fun getTaskById(taskId: String): Task? {
        delay(1000L)
        return _tasksFlow.value.find { it.id == taskId }
    }
}