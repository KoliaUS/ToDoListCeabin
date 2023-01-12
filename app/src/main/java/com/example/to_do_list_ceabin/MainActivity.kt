package com.example.to_do_list_ceabin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list_ceabin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskItemClickListener {


    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel by viewModels {
        TaskItemModelFactory((application as TodoAplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.pridatT.setOnClickListener {
            NewTask(null).show(supportFragmentManager, "NewTaskTag")
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {

        val mainActivity = this
        taskViewModel.taskList.observe(this) {
            binding.TodoRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it, mainActivity)
            }

        }

    }

    override fun editTaskItem(taskItem: TaskList) {
        NewTask(taskItem).show(supportFragmentManager, "NewTaskTag")
    }

    override fun completeTaskItem(taskItem: TaskList) {
        taskViewModel.SetCompleted(taskItem)
    }
}
