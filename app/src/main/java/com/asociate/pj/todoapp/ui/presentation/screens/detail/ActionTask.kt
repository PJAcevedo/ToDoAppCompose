package com.asociate.pj.todoapp.ui.presentation.screens.detail

import com.asociate.pj.todoapp.ui.domain.Category

sealed class ActionTask {
    data object SaveTask : ActionTask()
    data object Back : ActionTask()
    data class ChangeTaskCategory(val category: Category?) : ActionTask()
    data class ChangeTaskDone(val isTaskDone: Boolean) : ActionTask()
}