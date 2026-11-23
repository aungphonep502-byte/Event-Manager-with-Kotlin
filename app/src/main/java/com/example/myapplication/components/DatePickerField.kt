package com.example.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    // =================================================
    // DATE TEXT FIELD
    // =================================================

    OutlinedTextField(
        value = selectedDate,

        onValueChange = {
            // Date cannot be typed manually.
        },

        modifier = Modifier.fillMaxWidth(),

        label = {
            Text("Date")
        },

        placeholder = {
            Text("Select date")
        },

        leadingIcon = {

            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = "Date"
            )
        },

        trailingIcon = {

            IconButton(
                onClick = {
                    showDatePicker = true
                }
            ) {

                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Open date picker"
                )
            }
        },

        readOnly = true,

        singleLine = true,

        shape = RoundedCornerShape(16.dp)
    )

    // =================================================
    // DATE PICKER
    // =================================================

    if (showDatePicker) {

        val datePickerState =
            rememberDatePickerState()

        DatePickerDialog(

            onDismissRequest = {
                showDatePicker = false
            },

            confirmButton = {

                TextButton(
                    onClick = {

                        val selectedMillis =
                            datePickerState.selectedDateMillis

                        if (selectedMillis != null) {

                            val formatter =
                                SimpleDateFormat(
                                    "dd/MM/yyyy",
                                    Locale.getDefault()
                                )

                            val formattedDate =
                                formatter.format(
                                    Date(selectedMillis)
                                )

                            onDateSelected(
                                formattedDate
                            )
                        }

                        showDatePicker = false
                    }
                ) {

                    Text("OK")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {

                    Text("Cancel")
                }
            }
        ) {

            DatePicker(
                state = datePickerState
            )
        }
    }
}