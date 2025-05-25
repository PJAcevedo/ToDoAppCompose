package com.asociate.pj.todoapp.ui.domain

data class Task(
    val id: String,
    val title:String,
    val description:String?,
    val isCompleted:Boolean = false,
    val category: Category? = null
)