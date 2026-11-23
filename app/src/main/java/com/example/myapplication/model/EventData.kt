package com.example.myapplication.model

// This class represents one event stored in the SQLite database.
data class EventData(

    // Database ID
    val id: Int,

    // Event name
    val eventName: String,

    // Event date
    val date: String,

    // Event location
    val location: String,

    // Person or organisation organising the event
    val organizer: String,

    // Event type
    val type: String,

    // Event status - added for Logbook 3
    val status: String
)