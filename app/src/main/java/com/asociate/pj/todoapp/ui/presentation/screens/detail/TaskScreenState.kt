package com.asociate.pj.todoapp.ui.presentation.screens.detail

import com.asociate.pj.todoapp.ui.domain.Category

data class TaskScreenState(
    val taskName: String = "",
    val taskDescription: String? = null,
    val category: Category? = null,
    val isTaskDone: Boolean = false
)