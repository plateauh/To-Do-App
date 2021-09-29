package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
lateinit var recyclerView: RecyclerView
lateinit var addItemBtn: FloatingActionButton
lateinit var toDo: ToDo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toDo = ToDo()

        recyclerView = findViewById(R.id.rvItems)
        recyclerView.adapter = RecyclerViewAdapter(toDo.items)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addItemBtn = findViewById(R.id.addBtn)
        addItemBtn.setOnClickListener {
            Alert(this, toDo.items, recyclerView)
        }
    }
}