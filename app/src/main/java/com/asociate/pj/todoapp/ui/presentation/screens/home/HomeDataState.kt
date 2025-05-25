package com.asociate.pj.todoapp.ui.presentation.screens.home

import com.asociate.pj.todoapp.ui.domain.Task

data class HomeDataState(
    val date: String = "",
    val summary: String = "",
    val completedTask: List<Task> = emptyList(),
    val pendingTask: List<Task> = emptyList()
)
