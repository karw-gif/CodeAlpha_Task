package com.example.campusconnect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class EventListActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private val eventList = mutableListOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventlist)

        recyclerView = findViewById(R.id.recyclerEvents)
        recyclerView.layoutManager = LinearLayoutManager(this)
        eventAdapter = EventAdapter(eventList)
        recyclerView.adapter = eventAdapter

        database = FirebaseDatabase.getInstance().getReference("events")

        fetchEvents()
    }

    private fun fetchEvents() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                for (data in snapshot.children) {
                    val event = data.getValue(Event::class.java)
                    event?.let { eventList.add(it) }
                }
                eventAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("EventListActivity", "Failed to read events", error.toException())
            }
        })
    }
}
