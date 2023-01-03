package com.example.to_do_list_ceabin

import androidx.lifecycle.*
import com.example.to_do_list_ceabin.TaskList
import kotlinx.coroutines.launch
import java.time.LocalDate

class TaskViewModel(private val repository: TaskItemRepository): ViewModel()
{
    var taskList: LiveData<List<TaskList>> = repository.allTaskList.asLiveData()


    fun AddTaskItem(newTask: TaskList) = viewModelScope.launch {
        repository.insertTaskList(newTask)
    }

    fun updateTaskItem(taskItem: TaskList) = viewModelScope.launch {
        repository.updateTaskItem(taskItem)

    }
    fun SetCompleted(taskItem: TaskList) = viewModelScope.launch {
    if(!taskItem.isCompleted())
        taskItem.htv_c_string = TaskList.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(taskItem)
    }

}

class TaskItemModelFactory(private val repository: TaskItemRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(repository) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}