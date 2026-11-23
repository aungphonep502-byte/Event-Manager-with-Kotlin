package com.example.myapplication.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.EventData

private val BlueStart = Color(0xFF1565C0)

@Composable
fun EventCard(
    event: EventData,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()

            // White card
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )

            // Blue border on all four sides
            .border(
                width = 3.dp,
                color = BlueStart,
                shape = RoundedCornerShape(20.dp)
            )

            .padding(24.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // =================================================
        // EVENT NAME
        // =================================================

        Text(
            text = event.eventName,

            style = MaterialTheme.typography.titleLarge,

            color = BlueStart
        )

        // =================================================
        // DATE
        // =================================================

        EventInfoRow(
            icon = Icons.Default.CalendarMonth,
            text = event.date
        )

        // =================================================
        // LOCATION
        // =================================================

        EventInfoRow(
            icon = Icons.Default.LocationOn,
            text = event.location
        )

        // =================================================
        // ORGANIZER
        // =================================================

        EventInfoRow(
            icon = Icons.Default.Person,
            text = event.organizer
        )

        // =================================================
        // EVENT TYPE
        // =================================================

        EventInfoRow(
            icon = Icons.Default.Category,
            text = event.type
        )

        // =================================================
        // EVENT STATUS
        // =================================================

        EventInfoRow(
            icon = Icons.Default.Event,
            text = "Status: ${event.status}"
        )

        Spacer(
            modifier = Modifier.padding(2.dp)
        )

        // =================================================
        // EDIT + DELETE BUTTONS
        // =================================================

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Button(
                onClick = onEdit,

                modifier = Modifier.weight(1f),

                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueStart
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Edit,

                    contentDescription = "Edit"
                )

                Spacer(
                    modifier = Modifier.padding(3.dp)
                )

                Text("Edit")
            }

            Button(
                onClick = onDelete,

                modifier = Modifier.weight(1f),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F)
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,

                    contentDescription = "Delete"
                )

                Spacer(
                    modifier = Modifier.padding(3.dp)
                )

                Text("Delete")
            }
        }
    }
}


@Composable
fun EventInfoRow(
    icon: ImageVector,
    text: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        Icon(
            imageVector = icon,

            contentDescription = null,

            tint = BlueStart
        )

        Text(
            text = text,

            style = MaterialTheme.typography.bodyLarge,

            color = Color(0xFF212121)
        )
    }
}