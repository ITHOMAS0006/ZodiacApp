package com.example.zodiacapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // Define a data class to represent a zodiac sign
    data class ZodiacSign(val name: String, val imageResourceId: Int)

    // Define an adapter for the RecyclerView
    class ZodiacSignAdapter(private val zodiacSigns: List<ZodiacSign>) : RecyclerView.Adapter<ZodiacSignAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nameTextView: TextView = view.findViewById(R.id.zodiac_text)
            val imageView: ImageView = view.findViewById(R.id.zodiac_image)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_view_zodiac_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val zodiacSign = zodiacSigns[position]
            holder.nameTextView.text = zodiacSign.name
            holder.imageView.setImageResource(zodiacSign.imageResourceId)
        }

        override fun getItemCount(): Int {
            return zodiacSigns.size
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Define a list of all the zodiac signs, along with their image resource IDs
        val zodiacSigns = listOf(
            ZodiacSign("Aries", R.drawable.aries),
            ZodiacSign("Taurus", R.drawable.taurus),
            ZodiacSign("Gemini", R.drawable.gemini),
            ZodiacSign("Cancer", R.drawable.cancer),
            ZodiacSign("Leo", R.drawable.leo),
            ZodiacSign("Virgo", R.drawable.virgo),
            ZodiacSign("Libra", R.drawable.libra),
            ZodiacSign("Scorpio", R.drawable.scorpio),
            ZodiacSign("Sagittarius", R.drawable.sagittarius),
            ZodiacSign("Capricorn", R.drawable.capricorn),
            ZodiacSign("Aquarius", R.drawable.aquarius),
            ZodiacSign("Pisces", R.drawable.pisces)
        )

        // Get a reference to the RecyclerView in the layout
        val recyclerView = findViewById<RecyclerView>(R.id.am_recyclerview)

        // Create an adapter for the RecyclerView, passing in the list of zodiac signs
        val adapter = ZodiacSignAdapter(zodiacSigns)

        // Set the adapter on the RecyclerView
        recyclerView.adapter = adapter

        // Set the layout manager on the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}
