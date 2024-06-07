package com.example.beproductive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class slider1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider1)

        val startButton = findViewById<Button>(R.id.getstartedButton)
        startButton.setOnClickListener {
            val intent = Intent(this, instruction::class.java)
            startActivity(intent)
            finish()
        }
    }
}
