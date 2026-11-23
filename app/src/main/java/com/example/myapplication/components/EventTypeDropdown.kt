package com.example.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
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
fun EventTypeDropdown(
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {

    val eventTypes = listOf(
        "Conference",
        "Workshop",
        "Seminar",
        "Social",
        "Other"
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

            value = selectedType,

            onValueChange = {
                // User selects from dropdown.
            },

            readOnly = true,

            label = {
                Text("Event Type")
            },

            placeholder = {
                Text("Event type")
            },

            leadingIcon = {

                Icon(
                    imageVector = Icons.Default.Category,
                    contentDescription = "Event Type"
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

            eventTypes.forEach { type ->

                DropdownMenuItem(

                    text = {
                        Text(type)
                    },

                    onClick = {

                        onTypeSelected(type)

                        expanded = false
                    }
                )
            }
        }
    }
}