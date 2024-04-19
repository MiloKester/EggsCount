package com.example.eggscount

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

// Milo Kester 30063179
// 2024-00-00
// EggsCount
// AT1 Activity 5 - Develop an Android app that sorts eggs count per gross, dozen, and unit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // declaring fields that will be used
        val buttonCount : Button = findViewById(R.id.buttonCount)
        val numberEntry : TextView = findViewById(R.id.textInput)

        // auto displays as 0
        var units = 0
        var dozens = 0
        var grosses = 0
        displayResults(units, dozens, grosses)

        // on calc button click
        buttonCount.setOnClickListener {
            try {
                // parse value to int
                val string = numberEntry.getText().toString()
                val numToCount = string.toInt() // text field is limited to numbers only so this shouldn't error

                // calculate values
                units = numToCount % 12           // unit  = left overs
                dozens = (numToCount / 12) % 12   // dozen = 12 eggs
                grosses  = (numToCount / 12) / 12 // gross = 12 dozens

                displayResults(units, dozens, grosses) // display results
            }
            catch (e: java.lang.NumberFormatException) { // used if null or greater than integer
                Toast.makeText(this, "Enter A Number Between 0 and 2,147,483,648", Toast.LENGTH_SHORT).show()
                displayResults(0, 0 ,0)
            }
            catch (e: Exception) {
                println(e.message)
            }
            numberEntry.text = "" // clear for new entry
        }
    }

    private fun displayResults(units: Int, dozens: Int, grosses: Int) {
        val outputResult : TextView = findViewById(R.id.textResult)
        val stringDisplay : String = getString(R.string.display_count, units, dozens, grosses)
        outputResult.text = stringDisplay
    }
}