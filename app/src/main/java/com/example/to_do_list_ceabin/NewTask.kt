package com.example.to_do_list_ceabin

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.to_do_list_ceabin.databinding.FragmentNewTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime


class NewTask(var taskList: TaskList?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskBinding
    private lateinit var taskViewModel: TaskViewModel
    private var cas_h: LocalTime? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (taskList != null) {
            binding.TitulekNazev.text = "Upravit text"
            val editable = Editable.Factory.getInstance()
            binding.Nazev.text = editable.newEditable(taskList!!.nazev)
            binding.Popis.text = editable.newEditable(taskList!!.popis)
            if (taskList!!.cas() != null) {
                cas_h = taskList!!.cas()!!
                UpdateTimeButtonText()

            }
        } else {
            binding.TitulekNazev.text = "Nová činnost"
        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.Ulozit.setOnClickListener {
            UlozitL()
        }
        binding.TPickerB.setOnClickListener {
            openTimePicker()
        }
    }

    private fun openTimePicker() {
        if (cas_h == null)
            cas_h = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
            cas_h = LocalTime.of(selectedHour, selectedMinute)
            UpdateTimeButtonText()
        }
        val dialog_okno = TimePickerDialog(activity, listener, cas_h!!.hour, cas_h!!.minute, true)
        dialog_okno.setTitle("Výběr času")
        dialog_okno.show()

    }

    private fun UpdateTimeButtonText() {
        binding.TPickerB.text = String.format("%02d:%02d", cas_h!!.hour, cas_h!!.minute)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun UlozitL() {
        val nazev = binding.Nazev.text.toString()
        val popis = binding.Popis.text.toString()
        val cas_string = if (cas_h == null) null else TaskList.timeFormatter.format(cas_h)

        if (taskList == null) {
            val newTask = TaskList(nazev, popis, cas_string, null)
            taskViewModel.AddTaskItem(newTask)


        } else {
            taskList!!.nazev = nazev
            taskList!!.popis = popis
            taskList!!.cas_string = cas_string
            taskViewModel.updateTaskItem(taskList!!)
        }
        binding.Nazev.setText("")
        binding.Popis.setText("")
        dismiss()

    }
}



