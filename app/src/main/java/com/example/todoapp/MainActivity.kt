package com.example.todoapp

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
lateinit var recyclerView: RecyclerView
lateinit var addItemBtn: FloatingActionButton
lateinit var toDoList: ArrayList<ToDoItem>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toDoList = arrayListOf()

        recyclerView = findViewById(R.id.rvItems)
        recyclerView.adapter = RecyclerViewAdapter(toDoList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addItemBtn = findViewById(R.id.addBtn)
        addItemBtn.setOnClickListener {
            alert()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_option -> {
                deleteItems()
                recyclerView.adapter!!.notifyDataSetChanged()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun alert() {
        val dialogBuilder = AlertDialog.Builder(this)
        val input = EditText(this)
        dialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener { _, _ ->
            toDoList.add(ToDoItem(input.text.toString(), false))
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

    private fun deleteItems() {
        if (toDoList.isEmpty()) {
            Toast.makeText(this, "You have no item to delete!", Toast.LENGTH_SHORT).show()
            return
        }
        var plural = "items"
        var deleteCounter = 0
        for (item in toDoList){
            if (item.isSelected){
                toDoList.remove(item)
                deleteCounter++
            }
        }
        if (deleteCounter == 1) plural = "item"
        Toast.makeText(this, "$deleteCounter $plural deleted", Toast.LENGTH_SHORT).show()
    }
}