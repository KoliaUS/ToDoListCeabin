package com.example.to_do_list_ceabin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskList::class], version = 1, exportSchema = false)
abstract class TaskItemDatabase : RoomDatabase() {

    abstract fun taskItemD(): TaskItemD

    companion object {
        @Volatile
        private var INSTANCE: TaskItemDatabase? = null
        fun getDatabase(context: Context): TaskItemDatabase {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskItemDatabase::class.java,
                    "task_itemdatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}