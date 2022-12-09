package com.locosub.focus_work.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false
) : Parcelable {
    @Parcelize
    data class TaskResponse(
        val task: Task? = null,
        val key: String = ""
    ) : Parcelable
}
