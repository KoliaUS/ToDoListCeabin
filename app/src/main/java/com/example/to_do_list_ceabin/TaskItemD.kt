package com.example.to_do_list_ceabin

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskItemD {
    @Query("SELECT * FROM task_list_database ORDER BY ID ASC")
    fun allTaskList(): Flow<List<TaskList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskList(taskItem: TaskList)
    @Update
    suspend fun updateTaskList(taskItem: TaskList)

    @Delete
    suspend fun deleteTaskItem(taskItem: TaskList)
}
