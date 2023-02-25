package com.example.zodiacapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ZodiacSignAdapter(private val zodiacSigns: Array<String>) :
    RecyclerView.Adapter<ZodiacSignAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val zodiacImage: ImageView = view.findViewById(R.id.zodiac_image)
        val zodiacText: TextView = view.findViewById(R.id.zodiac_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rc_view_zodiac_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val zodiacSign = zodiacSigns[position]
        holder.zodiacText.text = zodiacSign
        holder.zodiacImage.setImageResource(getZodiacSignImageResource(zodiacSign))
    }

    override fun getItemCount(): Int {
        return zodiacSigns.size
    }

    private fun getZodiacSignImageResource(zodiacSign: String): Int {
        return when (zodiacSign) {
            "Aries" -> R.drawable.aries
            "Taurus" -> R.drawable.taurus
            "Gemini" -> R.drawable.gemini
            "Cancer" -> R.drawable.cancer
            "Leo" -> R.drawable.leo
            "Virgo" -> R.drawable.virgo
            "Libra" -> R.drawable.libra
            "Scorpio" -> R.drawable.scorpio
            "Sagittarius" -> R.drawable.sagittarius
            "Capricorn" -> R.drawable.capricorn
            "Aquarius" -> R.drawable.aquarius
            "Pisces" -> R.drawable.pisces
            else -> throw IllegalArgumentException("Invalid zodiac sign name")
        }
    }
}
