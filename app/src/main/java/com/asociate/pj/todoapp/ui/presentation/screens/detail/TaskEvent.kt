package com.asociate.pj.todoapp.ui.presentation.screens.detail

sealed class TaskEvent {
    data object TaskCreated : TaskEvent()
}