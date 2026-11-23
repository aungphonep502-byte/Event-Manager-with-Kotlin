package com.example.myapplication.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.EventData

private val BlueStart = Color(0xFF1565C0)
private val BlueEnd = Color(0xFF42A5F5)
private val BackgroundColor = Color(0xFFF5F8FF)

@Composable
fun EventListScreen(
    events: List<EventData>,
    onBack: () -> Unit,
    onEdit: (EventData) -> Unit,
    onDelete: (Int) -> Unit
) {

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

            Column {

                Text(
                    text = "All Events",

                    color = Color.White,

                    fontSize = 22.sp
                )

                Text(
                    text = "${events.size} event(s)",

                    color = Color.White.copy(
                        alpha = 0.85f
                    ),

                    fontSize = 13.sp
                )
            }
        }

        // =================================================
        // NO EVENTS
        // =================================================

        if (events.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),

                horizontalAlignment = Alignment.CenterHorizontally,

                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Event,

                    contentDescription = "No events",

                    tint = BlueStart,

                    modifier = Modifier.size(60.dp)
                )

                Spacer(
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = "No events yet",

                    fontSize = 20.sp
                )

                Spacer(
                    modifier = Modifier.size(6.dp)
                )

                Text(
                    text = "Add a new event to see it here.",

                    color = Color.Gray,

                    fontSize = 14.sp
                )
            }

        } else {

            // =================================================
            // EVENT LIST
            // =================================================

            LazyColumn(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(14.dp)
            ) {

                items(
                    items = events,
                    key = {
                        it.id
                    }
                ) { event ->

                    EventCard(

                        event = event,

                        onEdit = {
                            onEdit(event)
                        },

                        onDelete = {
                            onDelete(event.id)
                        }
                    )
                }
            }
        }
    }
}