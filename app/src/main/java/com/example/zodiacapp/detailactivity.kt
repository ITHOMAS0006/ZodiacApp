package com.example.zodiacapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class detailactivity : AppCompatActivity() {
    private val api: Zodiacapi = RetrofitInstance.api
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
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

        val sign = name?.lowercase() ?: ""
        if (sign.isNotEmpty()) {

            fetchDailyAffirmation(sign)
        } else {
            showToast("Error: Zodiac sign not provided")
        }

    }

    private fun fetchDailyAffirmation(sign: String) {
        coroutineScope.launch {
            try {
                val response = api.getDailyHoroscopes()
                val horoscope = response.find { it.sign.lowercase() == sign }
                if (horoscope != null) {
                    // Update the UI with the daily horoscope
                    findViewById<TextView>(R.id.affirmation_textview).text = horoscope.title
                } else {
                    showToast("Error fetching daily horoscope")
                }
            } catch (e: Exception) {
                showToast("Error fetching daily horoscope: ${e.message}")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}