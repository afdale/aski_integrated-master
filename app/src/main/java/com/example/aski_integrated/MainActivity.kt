package com.example.aski_integrated

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var panggilanandonms: ImageButton
    private lateinit var breakdown: ImageButton
    private lateinit var planning: ImageButton
    private lateinit var improvement: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            panggilanandonms = findViewById(R.id.panggilanandonms)
             panggilanandonms.setOnClickListener {
            startActivity(Intent(this, ActivityAndon::class.java))
        }

            breakdown = findViewById(R.id.breakdown)
            breakdown.setOnClickListener {
                startActivity(Intent(this, BreakdownAdapter::class.java))
            }

            planning = findViewById(R.id.planning)
            planning.setOnClickListener {
            startActivity(Intent(this, PlanningAdapter::class.java))
        }

        improvement = findViewById(R.id.improvement)
        improvement.setOnClickListener {
            startActivity(Intent(this, ImprovementAdapter::class.java))
        }

        }catch (ex:Exception){
            Toast.makeText(this, "$ex", Toast.LENGTH_LONG).show()
        }


    }
}


