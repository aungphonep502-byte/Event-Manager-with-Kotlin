package com.example.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventStatusDropdown(
    selectedStatus: String,
    onStatusSelected: (String) -> Unit
) {

    val statuses = listOf(
        "Upcoming",
        "Ongoing",
        "Completed",
        "Cancelled"
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(

        expanded = expanded,

        onExpandedChange = {
            expanded = !expanded
        },

        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedTextField(

            value = selectedStatus,

            onValueChange = {
                // User selects from dropdown.
            },

            readOnly = true,

            label = {
                Text("Event Status")
            },

            placeholder = {
                Text("Select event status")
            },

            leadingIcon = {

                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = "Event Status"
                )
            },

            trailingIcon = {

                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),

            shape = RoundedCornerShape(16.dp)
        )

        ExposedDropdownMenu(

            expanded = expanded,

            onDismissRequest = {
                expanded = false
            }
        ) {

            statuses.forEach { status ->

                DropdownMenuItem(

                    text = {
                        Text(status)
                    },

                    onClick = {

                        onStatusSelected(status)

                        expanded = false
                    }
                )
            }
        }
    }
}