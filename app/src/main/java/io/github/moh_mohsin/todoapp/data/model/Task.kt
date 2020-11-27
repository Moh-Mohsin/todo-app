package io.github.moh_mohsin.todoapp.data.model

import java.io.Serializable

data class Task(
    val id: Int,
    val title: String,
    val description: String,
): Serializable