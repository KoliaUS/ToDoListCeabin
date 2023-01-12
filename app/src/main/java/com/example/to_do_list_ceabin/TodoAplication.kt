package com.example.to_do_list_ceabin

import android.app.Application

class TodoAplication : Application() {
    private val database by lazy { TaskItemDatabase.getDatabase(this) }
    val repository by lazy { TaskItemRepository(database.taskItemD()) }
}