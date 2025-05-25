package com.asociate.pj.todoapp.ui.presentation.screens.home

sealed class HomeScreenEvent {
    data object UpdatedTask : HomeScreenEvent()
    data object DeleteAllTasks : HomeScreenEvent()
    data object DeletedTask : HomeScreenEvent()
}