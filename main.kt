package com.example.year_loader

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit
import android.os.Handler
import android.os.Looper
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tHandler: Handler
    private lateinit var dHandler: Handler
    private lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Time
        var currentTimeTextView1 = findViewById<TextView>(R.id.currentTimeTextView)
        var currentTime = getCurrentTime()
        //currentTimeTextView1.text = currentTime
        tHandler = Handler()
        updateCurrentTime()

        // Day
        dHandler = Handler()
        updateCurrentDay()

        // Month
        mHandler = Handler()
        updateCurrentMonth()


        // Buttons
        val button1 = findViewById<Button>(R.id.b1)
        button1.setOnClickListener{
            val intent = Intent(this, DayLoader::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.b2)
        button2.setOnClickListener{
            val intent = Intent(this, MonthLoader::class.java)
            startActivity(intent)
        }

        val button3 = findViewById<Button>(R.id.b3)
        button3.setOnClickListener{
            val intent = Intent(this, YearLoader::class.java)
            startActivity(intent)
        }
    }

    // Time
    private fun getCurrentTime(): String {
        var dateFormat1 = SimpleDateFormat("HH:mm", Locale.getDefault())
        var currentTime1 = Calendar.getInstance().time
        return dateFormat1.format(currentTime1)
    }

    private fun updateCurrentTime() {
        val currentTime = getCurrentTime()
        var currentTimeTextView1 = findViewById<TextView>(R.id.currentTimeTextView)
        currentTimeTextView1.text = currentTime
        tHandler.postDelayed({ updateCurrentTime() }, 1000)
    }

    // Day
    private fun updateCurrentDay() {
        val calendar1 = GregorianCalendar()
        calendar1.time = Date()
        val currentDay = (calendar1.time).toString()
        var currentDateTextView2 = findViewById<TextView>(R.id.currentDateTextView)
        val day = calendar1.get(Calendar.DAY_OF_MONTH)
        currentDateTextView2.text = day.toString()
        dHandler.postDelayed({ updateCurrentDay() }, 1000)
    }

    // Month
    private fun updateCurrentMonth() {
        val textView = findViewById<TextView>(R.id.currentDateMonth)
        val calendar = Calendar.getInstance()
        val monthName = DateFormatSymbols().months[calendar.get(Calendar.MONTH)]
        textView.text = monthName
        mHandler.postDelayed({ updateCurrentDay() }, 1000)
    }
}
