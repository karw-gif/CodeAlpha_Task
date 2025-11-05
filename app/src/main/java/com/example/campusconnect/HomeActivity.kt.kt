package com.example.campusconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnEvents: Button = findViewById(R.id.btnEvents)
        val btnNotifications: Button = findViewById(R.id.btnNotifications)
        val btnAddEvent: Button = findViewById(R.id.btnAddEvent) // optional add button in XML

        // Navigate to Event List
        btnEvents.setOnClickListener {
            val intent = Intent(this, EventListActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Notifications screen
        btnNotifications.setOnClickListener {
            val intent = Intent(this, NotificationsActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Add Event screen
        btnAddEvent?.setOnClickListener {
            val intent = Intent(this, AddEventActivity::class.java)
            startActivity(intent)
        }
    }
}
