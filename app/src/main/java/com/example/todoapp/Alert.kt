package com.example.todoapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class Alert (private val context: Context, private val toDoList: ArrayList<String>, val recyclerView: RecyclerView) {

    init {
        val dialogBuilder = AlertDialog.Builder(context)
        val input = EditText(context)
        dialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener { _, _ ->
                toDoList.add(input.text.toString())
                recyclerView.adapter!!.notifyDataSetChanged()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener{ dialog, _ ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("New Item")
        alert.setView(input)
        alert.show()
    }
}