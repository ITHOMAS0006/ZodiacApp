package com.example.zodiacapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class detailactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailactivity)

        // Get the zodiac data from the intent
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val symbol = intent.getStringExtra("zdsymbol")
        val month = intent.getStringExtra("month")

        // Set the zodiac data in the TextViews
        findViewById<TextView>(R.id.name_textview).text = name
        findViewById<TextView>(R.id.description_textview).text = description
        findViewById<TextView>(R.id.symbol_textview).text = symbol
        findViewById<TextView>(R.id.month_textview).text = month
    }

}