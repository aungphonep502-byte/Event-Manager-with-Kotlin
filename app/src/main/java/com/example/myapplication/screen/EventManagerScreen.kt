package com.example.myapplication.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BlueStart = Color(0xFF1565C0)
private val BlueEnd = Color(0xFF42A5F5)
private val BackgroundColor = Color(0xFFF5F8FF)

@Composable
fun EventManagerScreen(
    onAddEvent: () -> Unit,
    onViewEvents: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {

        // =================================================
        // BLUE HEADER
        // =================================================

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            BlueStart,
                            BlueEnd
                        )
                    ),
                    shape = RoundedCornerShape(
                        bottomStart = 28.dp,
                        bottomEnd = 28.dp
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),

                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Event,

                    contentDescription = "Event Manager",

                    tint = Color.White,

                    modifier = Modifier.size(42.dp)
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Event Manager",

                    color = Color.White,

                    fontSize = 28.sp,

                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Manage your events",

                    color = Color.White.copy(
                        alpha = 0.9f
                    ),

                    fontSize = 14.sp
                )
            }
        }

        // =================================================
        // CONTENT
        // =================================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Text(
                text = "Event Management",

                style = MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Choose what you want to do.",

                color = Color.Gray,

                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // =================================================
            // ADD EVENT
            // =================================================

            ManagerOptionCard(
                icon = Icons.Default.Add,

                title = "Add New Event",

                description = "Create a new event entry.",

                onClick = onAddEvent
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // =================================================
            // VIEW EVENTS
            // =================================================

            ManagerOptionCard(
                icon = Icons.Default.List,

                title = "View All Events",

                description = "View all saved event entries.",

                onClick = onViewEvents
            )
        }
    }
}


// =========================================================
// REUSABLE CARD
// =========================================================

@Composable
private fun ManagerOptionCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,

        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        color = BlueStart.copy(
                            alpha = 0.10f
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,

                    contentDescription = title,

                    tint = BlueStart,

                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(
                modifier = Modifier.size(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,

                    fontSize = 19.sp,

                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = description,

                    color = Color.Gray,

                    fontSize = 13.sp
                )
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,

                contentDescription = "Open",

                tint = BlueStart,

                modifier = Modifier.size(30.dp)
            )
        }
    }
}