package com.example.to_do_list_ceabin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.to_do_list_ceabin.R.drawable.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "task_list_database")

class TaskList (
    @ColumnInfo(name = "nazev") var nazev: String,
    @ColumnInfo(name =  "popis") var popis: String,
    @ColumnInfo(name =  "cas_string") var cas_string: String?,
    @ColumnInfo(name =  "htv_c_string") var htv_c_string: String?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
        )
 {
     fun htv_c(): LocalDate? = if(htv_c_string == null) null
     else LocalDate.parse(htv_c_string, dateFormatter)

     fun cas(): LocalTime? = if(cas_string == null) null
     else LocalTime.parse(cas_string, timeFormatter)

     fun isCompleted() = htv_c() != null
     fun imageResource(): Int = if(isCompleted()) ic_check_complete else ic_uncheck

     companion object {
         val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME
         val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE

     }

}