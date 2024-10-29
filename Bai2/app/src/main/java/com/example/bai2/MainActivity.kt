package com.example.bai2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val inputNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val showButton = findViewById<Button>(R.id.showButton)
        val listView = findViewById<ListView>(R.id.listView)
        val errorText = findViewById<TextView>(R.id.errorText)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showButton.setOnClickListener {
            errorText.text = ""
            val n = inputNumber.text.toString().toIntOrNull()
            if (n == null || n < 1) {
                errorText.text = "Vui lòng nhập một số nguyên dương hợp lệ."
                return@setOnClickListener
            }
            val results = when (radioGroup.checkedRadioButtonId) {
                R.id.radioEven -> (1..n).filter { it % 2 == 0 }
                R.id.radioOdd -> (1..n).filter { it % 2 != 0 }
                R.id.radioSquare -> (1..n).filter { Math.sqrt(it.toDouble()).toInt().let { sq -> sq * sq == it } }
                else -> emptyList()
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listView.adapter = adapter
        }
    }
}