package com.example.to_do_list_ceabin

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TaskItemRepository(private val TaskItemD: TaskItemD)
{
    val allTaskList: Flow<List<TaskList>> = TaskItemD.allTaskList()

    @WorkerThread
    suspend fun insertTaskList(taskList: TaskList)
    {
        TaskItemD.insertTaskList(taskList)
    }
    @WorkerThread
    suspend fun updateTaskItem(taskList: TaskList)
    {
        TaskItemD.updateTaskList(taskList)
    }
}