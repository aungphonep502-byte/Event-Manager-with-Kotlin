package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import com.example.myapplication.database.DatabaseHelper
import com.example.myapplication.model.EventData
import com.example.myapplication.screen.EventForm
import com.example.myapplication.screen.EventListScreen
import com.example.myapplication.screen.EventManagerScreen

class MainActivity : ComponentActivity() {

    // Database helper
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        // Create database helper.
        databaseHelper = DatabaseHelper(this)

        setContent {

            // =================================================
            // CURRENT SCREEN
            // =================================================

            var currentScreen by remember {
                mutableStateOf("manager")
            }

            // =================================================
            // SELECTED EVENT
            // =================================================
            // null = adding a new event
            // event = editing an existing event

            var selectedEvent by remember {
                mutableStateOf<EventData?>(null)
            }

            // =================================================
            // EVENTS FROM DATABASE
            // =================================================

            var events by remember {

                mutableStateOf(
                    databaseHelper.getAllEvents()
                )
            }

            // =================================================
            // SCREEN NAVIGATION
            // =================================================

            when (currentScreen) {

                // =================================================
                // EVENT MANAGER
                // =================================================

                "manager" -> {

                    EventManagerScreen(

                        onAddEvent = {

                            // No selected event means
                            // we are adding a new event.
                            selectedEvent = null

                            currentScreen = "form"
                        },

                        onViewEvents = {

                            // Get the latest data
                            // from SQLite.
                            events =
                                databaseHelper.getAllEvents()

                            currentScreen = "list"
                        }
                    )
                }

                // =================================================
                // EVENT FORM
                // =================================================

                "form" -> {

                    EventForm(

                        existingEvent = selectedEvent, //edit

                        onBack = {

                            currentScreen = "manager"
                        },

                        onEventSaved = {

                                eventName,
                                date,
                                location,
                                organizer,
                                type,
                                status ->

                            // =================================================
                            // INSERT NEW EVENT
                            // =================================================

                            if (selectedEvent == null) {

                                databaseHelper.insertEvent(

                                    eventName =
                                        eventName,

                                    date =
                                        date,

                                    location =
                                        location,

                                    organizer =
                                        organizer,

                                    type =
                                        type,

                                    status =
                                        status
                                )

                            } else {

                                // =================================================
                                // UPDATE EXISTING EVENT
                                // =================================================

                                databaseHelper.updateEvent(

                                    id =
                                        selectedEvent!!.id,

                                    eventName =
                                        eventName,

                                    date =
                                        date,

                                    location =
                                        location,

                                    organizer =
                                        organizer,

                                    type =
                                        type,

                                    status =
                                        status
                                )
                            }

                            // Refresh the event list
                            // from the database.
                            events =
                                databaseHelper.getAllEvents()

                            // Clear selected event.
                            selectedEvent = null

                            // Show the event list.
                            currentScreen = "list"
                        }
                    )
                }

                // =================================================
                // EVENT LIST
                // =================================================

                "list" -> {

                    EventListScreen(

                        events = events,

                        onBack = {

                            currentScreen =
                                "manager"
                        },

                        // =================================================
                        // EDIT
                        // =================================================

                        onEdit = { event ->

                            // Store the event
                            // that the user wants to edit.
                            selectedEvent = event

                            // Open the same EventForm.
                            currentScreen = "form"
                        },

                        // =================================================
                        // DELETE
                        // =================================================

                        onDelete = { id ->

                            databaseHelper.deleteEvent(id)

                            // Refresh list after deleting.
                            events =
                                databaseHelper.getAllEvents()
                        }
                    )
                }
            }
        }
    }

    // =========================================================
    // CLOSE DATABASE
    // =========================================================

    override fun onDestroy() {

        databaseHelper.close()

        super.onDestroy()
    }
}