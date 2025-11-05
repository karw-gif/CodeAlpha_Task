package com.example.campusconnect

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class AddEventActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var etDate: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDescription)
        etDate = findViewById(R.id.etDate)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val date = etDate.text.toString().trim()

            if(title.isEmpty() || description.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val database = FirebaseDatabase.getInstance().getReference("events")
            val event = Event(title, description, date)
            database.push().setValue(event)
                .addOnSuccessListener {
                    Toast.makeText(this, "Event added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to add event: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
