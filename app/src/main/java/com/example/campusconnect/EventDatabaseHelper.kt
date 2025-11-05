package com.example.campusconnect

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor

class EventDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "campus_events.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_EVENTS = "events"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_TYPE = "type"
        const val COLUMN_DATE = "date"
        const val COLUMN_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_EVENTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT,
                $COLUMN_TYPE TEXT,
                $COLUMN_DATE TEXT,
                $COLUMN_DESCRIPTION TEXT
            )
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EVENTS")
        onCreate(db)
    }

    fun addEvent(title: String, type: String, date: String, description: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_TYPE, type)
        values.put(COLUMN_DATE, date)
        values.put(COLUMN_DESCRIPTION, description)
        db.insert(TABLE_EVENTS, null, values)
        db.close()
    }

    fun getAllEvents(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_EVENTS ORDER BY $COLUMN_DATE ASC", null)
    }
}
