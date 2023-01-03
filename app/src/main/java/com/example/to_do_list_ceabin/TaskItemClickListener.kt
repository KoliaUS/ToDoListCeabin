package com.example.to_do_list_ceabin

interface TaskItemClickListener
{
    fun editTaskItem(taskItem: TaskList)
    fun completeTaskItem(taskItem: TaskList)
}