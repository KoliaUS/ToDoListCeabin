package com.example.to_do_list_ceabin

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list_ceabin.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener


) : RecyclerView.ViewHolder(binding.root) {
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    fun bindTaskItem(taskItem: TaskList) {
        binding.Nazev.text = taskItem.nazev
        if (taskItem.isCompleted()) {
            binding.Nazev.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.Cas.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        binding.CompleteB.setImageResource(taskItem.imageResource())
        binding.CompleteB.setColorFilter(taskItem.imageColor(context))

        binding.CompleteB.setOnClickListener {
            clickListener.completeTaskItem(taskItem)
        }
        binding.taskCellCountainer.setOnClickListener {
            clickListener.editTaskItem(taskItem)
        }

        if (taskItem.cas() != null)
            binding.Cas.text = timeFormat.format(taskItem.cas())
        else
            binding.Cas.text = ""

    }
}