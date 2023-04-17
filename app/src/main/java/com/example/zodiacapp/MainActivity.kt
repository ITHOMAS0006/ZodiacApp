package com.example.zodiacapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zodiacapp.zodiacdatabase
import com.example.zodiacapp.zodiacdao

import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity()  {

    // Define a data class to represent a zodiac sign
    data class ZodiacSign(val name: String, val imageResourceId: Int, val description: String, val symbol : String, val month: String)

    // Define an adapter for the RecyclerView
    class ZodiacSignAdapter(private val zodiacSigns: List<ZodiacSign>,private val zodiacDao: zodiacdao,private val coroutineScope: CoroutineScope) : RecyclerView.Adapter<ZodiacSignAdapter.ViewHolder>() {

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

            holder.itemView.setOnClickListener {
                coroutineScope.launch {
                listener?.onItemClick(zodiacDao.getZodiacByName(zodiacSign.name)) }
            }

        }

        override fun getItemCount(): Int {
            return zodiacSigns.size
        }
        interface OnItemClickListener {

            fun onItemClick(zodiac: zodiacdb)
        }

        var listener: OnItemClickListener? = null

        fun setOnItemClickListener(listener: OnItemClickListener) {
            this.listener = listener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainScope = CoroutineScope(Dispatchers.Main)


        // Define a list of all the zodiac signs, along with their image resource IDs
        val zodiacSigns = listOf(
            ZodiacSign("Aries", R.drawable.aries, "Courageous and Energetic.", "Ram", "April"),
            ZodiacSign("Taurus", R.drawable.taurus, "Known for being reliable, practical, ambitious and sensual.", "Bull", "May"),
            ZodiacSign("Gemini", R.drawable.gemini, "Gemini-born are clever and intellectual.", "Twins", "June"),
            ZodiacSign("Cancer", R.drawable.cancer, "Tenacious, loyal and sympathetic.", "Crab", "July"),
            ZodiacSign("Leo", R.drawable.leo, "Warm, action-oriented and driven by the desire to be loved and admired.", "Lion", "August"),
            ZodiacSign("Virgo", R.drawable.virgo, "Methodical, meticulous, analytical and mentally astute.", "Virgin", "September"),
            ZodiacSign("Libra", R.drawable.libra, "Librans are famous for maintaining balance and harmony.", "Scales", "October"),
            ZodiacSign("Scorpio", R.drawable.scorpio, "Strong willed and mysterious.", "Scorpion", "November"),
            ZodiacSign("Sagittarius", R.drawable.sagittarius, "Born adventurers.", "Archer", "December"),
            ZodiacSign("Capricorn", R.drawable.capricorn, "The most determined sign in the Zodiac.", "Goat", "January"),
            ZodiacSign("Aquarius", R.drawable.aquarius, "Humanitarians to the core", "Water Bearer", "February"),
            ZodiacSign("Pisces", R.drawable.pisces, "Proverbial dreamers of the Zodiac.", "Fish", "March")
        )



        // Get a reference to the RecyclerView in the layout
        val recyclerView = findViewById<RecyclerView>(R.id.am_recyclerview)

        // Create an adapter for the RecyclerView, passing in the list of zodiac signs
        val adapterinstance = ZodiacSignAdapter(zodiacSigns, zodiacDao = zodiacdatabase.getDatabase(applicationContext).zodiacDao(),coroutineScope = mainScope)

        // Set the adapter on the RecyclerView
        recyclerView.adapter = adapterinstance

        // Set the layout manager on the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val db = zodiacdatabase.getDatabase(applicationContext)
        var zodiacDao: zodiacdao = db.zodiacDao()
        mainScope.launch {
            withContext(Dispatchers.IO) {
        for (zodiac in zodiacSigns) {

            val zodiacdb = zodiacdb(zodiac.name, zodiac.imageResourceId, zodiac.description,zodiac.symbol, zodiac.month)
            val existingZodiac = zodiacDao.getZodiacByName(zodiac.name)
            if (existingZodiac == null) {
                zodiacDao.insertZodiacs(zodiacdb)
            }



            }

        } }



        adapterinstance.setOnItemClickListener(object : com.example.zodiacapp.MainActivity.ZodiacSignAdapter.OnItemClickListener {
            override fun onItemClick(zodiac: zodiacdb) {
                // Retrieve the corresponding zodiac data from the database
                mainScope.launch {
                    val selectedZodiac = zodiacDao.getZodiacByName(zodiac.name)

                    // Start the DetailActivity and pass the selected zodiac data
                    // Start the DetailActivity and pass the selected zodiac data
                    val intent = Intent(this@MainActivity, detailactivity::class.java)
                    intent.putExtra("name", selectedZodiac.name)
                    intent.putExtra("description", selectedZodiac.description)
                    intent.putExtra("zdsymbol", selectedZodiac.zdsymbol)
                    intent.putExtra("month", selectedZodiac.month)
                    startActivity(intent)
                }

              //  startActivity(intent)
            }
        })
    }

}
