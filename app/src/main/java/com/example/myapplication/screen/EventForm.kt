package com.example.myapplication.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.components.DatePickerField
import com.example.myapplication.components.EventStatusDropdown
import com.example.myapplication.components.EventTypeDropdown
import com.example.myapplication.model.EventData

private val BlueStart = Color(0xFF1565C0)
private val BlueEnd = Color(0xFF42A5F5)
private val BackgroundColor = Color(0xFFF5F8FF)

@Composable
fun EventForm(
    existingEvent: EventData?,
    onBack: () -> Unit,

    onEventSaved: (
        String,
        String,
        String,
        String,
        String,
        String
    ) -> Unit
) {

    // =================================================
    // FORM VALUES
    // =================================================

    var eventName by remember {
        mutableStateOf(
            existingEvent?.eventName ?: ""
        )
    }

    var date by remember {
        mutableStateOf(
            existingEvent?.date ?: ""
        )
    }

    var location by remember {
        mutableStateOf(
            existingEvent?.location ?: ""
        )
    }

    var organizer by remember {
        mutableStateOf(
            existingEvent?.organizer ?: ""
        )
    }

    var eventType by remember {
        mutableStateOf(
            existingEvent?.type ?: ""
        )
    }

    // New field for Logbook 3
    var eventStatus by remember {
        mutableStateOf(
            existingEvent?.status ?: ""
        )
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    // Check whether we are editing an existing event.
        val isEditMode = existingEvent != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {

        // =================================================
        // HEADER
        // =================================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            BlueStart,
                            BlueEnd
                        )
                    )
                )
                .padding(
                    start = 8.dp,
                    end = 16.dp,
                    top = 20.dp,
                    bottom = 20.dp
                ),

            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,

                    contentDescription = "Back",

                    tint = Color.White
                )
            }

            Text(
                    text = if (isEditMode) {
                    "Edit Event"
                } else {
                    "Add New Event"
                },

                color = Color.White,

                fontSize = 22.sp,

                fontWeight = FontWeight.Bold
            )
        }

        // =================================================
        // FORM
        // =================================================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            Text(
                text = "Event Details",

                fontSize = 22.sp,

                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Enter the required information for the event.",

                color = Color.Gray,

                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            // =================================================
            // EVENT NAME
            // =================================================

            OutlinedTextField(
                value = eventName,

                onValueChange = {
                    eventName = it
                    errorMessage = ""
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Event Name")
                },

                placeholder = {
                    Text("Enter event name")
                },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Event,

                        contentDescription = "Event Name"
                    )
                },

                singleLine = true,

                shape = RoundedCornerShape(16.dp)
            )

            // =================================================
            // DATE
            // =================================================

            DatePickerField(
                selectedDate = date,

                onDateSelected = {
                    date = it
                    errorMessage = ""
                }
            )

            // =================================================
            // LOCATION
            // =================================================

            OutlinedTextField(
                value = location,

                onValueChange = {
                    location = it
                    errorMessage = ""
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Location")
                },

                placeholder = {
                    Text("Enter location")
                },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,

                        contentDescription = "Location"
                    )
                },

                singleLine = true,

                shape = RoundedCornerShape(16.dp)
            )

            // =================================================
            // ORGANIZER
            // =================================================

            OutlinedTextField(
                value = organizer,

                onValueChange = {
                    organizer = it
                    errorMessage = ""
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Organizer")
                },

                placeholder = {
                    Text("Enter organizer name")
                },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,

                        contentDescription = "Organizer"
                    )
                },

                singleLine = true,

                shape = RoundedCornerShape(16.dp)
            )

            // =================================================
            // EVENT TYPE
            // =================================================

            EventTypeDropdown(
                selectedType = eventType,

                onTypeSelected = {
                    eventType = it
                    errorMessage = ""
                }
            )

            // =================================================
            // EVENT STATUS
            // =================================================

            EventStatusDropdown(
                selectedStatus = eventStatus,

                onStatusSelected = {
                    eventStatus = it
                    errorMessage = ""
                }
            )

            // =================================================
            // VALIDATION ERROR
            // =================================================

            if (errorMessage.isNotEmpty()) {

                Text(
                    text = errorMessage,

                    color = Color.Red,

                    fontSize = 14.sp,

                    fontWeight = FontWeight.Medium
                )
            }

            // =================================================
            // SAVE / UPDATE BUTTON
            // =================================================

            Button(
                onClick = {

                    // Check Event Name
                    if (eventName.isBlank()) {

                        errorMessage =
                            "Please enter the event name."
                    }

                    // Check Date
                    else if (date.isBlank()) {

                        errorMessage =
                            "Please select a date."
                    }

                    // Check Location
                    else if (location.isBlank()) {

                        errorMessage =
                            "Please enter the location."
                    }

                    // Check Organizer
                    else if (organizer.isBlank()) {

                        errorMessage =
                            "Please enter the organizer."
                    }

                    // Check Event Type
                    else if (eventType.isBlank()) {

                        errorMessage =
                            "Please select an event type."
                    }

                    // Check Event Status
                    else if (eventStatus.isBlank()) {

                        errorMessage =
                            "Please select an event status."
                    }

                    // Everything is valid
                    else {

                        onEventSaved(
                            eventName.trim(),
                            date,
                            location.trim(),
                            organizer.trim(),
                            eventType,
                            eventStatus
                        )
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueStart
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Save,

                    contentDescription = "Save"
                )

                Text(
                    text = if (isEditMode) {
                        "Update Event"
                    } else {
                        "Save Event"
                    },

                    modifier = Modifier.padding(
                        start = 8.dp
                    ),

                    fontSize = 16.sp,

                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }
    }
}