package com.example.myapplication.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.model.EventData

class DatabaseHelper(
    context: Context
) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {

        // Database name
        private const val DATABASE_NAME = "event_manager.db"

        // Database version
        private const val DATABASE_VERSION = 1

        // Table name
        private const val TABLE_EVENTS = "events"

        // Column names
        private const val COLUMN_ID = "id"
        private const val COLUMN_EVENT_NAME = "event_name"
        private const val COLUMN_EVENT_DATE = "event_date"
        private const val COLUMN_LOCATION = "location"
        private const val COLUMN_ORGANIZER = "organizer"
        private const val COLUMN_EVENT_TYPE = "event_type"
        private const val COLUMN_EVENT_STATUS = "event_status"
    }

    // =====================================================
    // CREATE DATABASE TABLE
    // =====================================================

    override fun onCreate(db: SQLiteDatabase) {

        val createTable = """
            CREATE TABLE $TABLE_EVENTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EVENT_NAME TEXT NOT NULL,
                $COLUMN_EVENT_DATE TEXT NOT NULL,
                $COLUMN_LOCATION TEXT NOT NULL,
                $COLUMN_ORGANIZER TEXT NOT NULL,
                $COLUMN_EVENT_TYPE TEXT NOT NULL,
                $COLUMN_EVENT_STATUS TEXT NOT NULL
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    // =====================================================
    // DATABASE UPGRADE
    // =====================================================

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        // Delete old table when database version changes.
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EVENTS")

        // Create the new table.
        onCreate(db)
    }

    // =====================================================
    // CREATE / INSERT
    // =====================================================

    fun insertEvent(
        eventName: String,
        date: String,
        location: String,
        organizer: String,
        type: String,
        status: String
    ): Long {

        val db = writableDatabase

        val values = ContentValues()

        values.put(
            COLUMN_EVENT_NAME,
            eventName
        )

        values.put(
            COLUMN_EVENT_DATE,
            date
        )

        values.put(
            COLUMN_LOCATION,
            location
        )

        values.put(
            COLUMN_ORGANIZER,
            organizer
        )

        values.put(
            COLUMN_EVENT_TYPE,
            type
        )

        values.put(
            COLUMN_EVENT_STATUS,
            status
        )

        // Insert the event into the database.
        val result = db.insert(
            TABLE_EVENTS,
            null,
            values
        )

        db.close()

        return result
    }

    // =====================================================
    // READ / GET ALL EVENTS
    // =====================================================

    fun getAllEvents(): List<EventData> {

        val eventList = mutableListOf<EventData>()

        val db = readableDatabase

        val cursor = db.query(
            TABLE_EVENTS,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_ID DESC"
        )

        // Read each row from the database.
        if (cursor.moveToFirst()) {

            do {

                val id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_ID
                    )
                )

                val eventName = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_EVENT_NAME
                    )
                )

                val date = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_EVENT_DATE
                    )
                )

                val location = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_LOCATION
                    )
                )

                val organizer = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_ORGANIZER
                    )
                )

                val type = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_EVENT_TYPE
                    )
                )

                val status = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        COLUMN_EVENT_STATUS
                    )
                )

                eventList.add(
                    EventData(
                        id = id,
                        eventName = eventName,
                        date = date,
                        location = location,
                        organizer = organizer,
                        type = type,
                        status = status
                    )
                )

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return eventList
    }

    // =====================================================
    // UPDATE
    // =====================================================

    fun updateEvent(
        id: Int,
        eventName: String,
        date: String,
        location: String,
        organizer: String,
        type: String,
        status: String
    ): Int {

        val db = writableDatabase

        val values = ContentValues()

        values.put(
            COLUMN_EVENT_NAME,
            eventName
        )

        values.put(
            COLUMN_EVENT_DATE,
            date
        )

        values.put(
            COLUMN_LOCATION,
            location
        )

        values.put(
            COLUMN_ORGANIZER,
            organizer
        )

        values.put(
            COLUMN_EVENT_TYPE,
            type
        )

        values.put(
            COLUMN_EVENT_STATUS,
            status
        )

        val result = db.update(
            TABLE_EVENTS,
            values,
            "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )

        db.close()

        return result
    }

    // =====================================================
    // DELETE
    // =====================================================

    fun deleteEvent(
        id: Int
    ): Int {

        val db = writableDatabase

        val result = db.delete(
            TABLE_EVENTS,
            "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )

        db.close()

        return result
    }
}