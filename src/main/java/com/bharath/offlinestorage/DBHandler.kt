package com.bharath.offlinestorage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList


class DBHandler  // creating a constructor for our database handler.
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // below method is for creating a database by running a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + AGE_COL + " TEXT,"
                + MAILID_COL + " TEXT,"
                + PHONENUM_COL + " TEXT)")

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query)
    }

    // this method is use to add new course to our sqlite database.
    fun addNewDetails(
        Name: String?,
        Age: String?,
        MailId: String?,
        PhoneNum: String?
    ) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, Name)
        values.put(AGE_COL, Age)
        values.put(MAILID_COL, MailId)
        values.put(PHONENUM_COL, PhoneNum)

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values)

        // at last we are closing our
        // database after adding database.
        db.close()
    }

    // we have created a new method for reading all the courses.
    fun readCourses(): ArrayList<CourseModal> {
        // on below line we are creating a
        // database for reading our database.
        val db = this.readableDatabase

        // on below line we are creating a cursor with query to read data from database.
        val cursorDetails = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

        // on below line we are creating a new array list.
        val detailModalArrayList = ArrayList<CourseModal>()

        // moving our cursor to first position.
        if (cursorDetails.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                detailModalArrayList.add(
                    CourseModal(
                        cursorDetails.getString(1),
                        cursorDetails.getString(4),
                        cursorDetails.getString(2),
                        cursorDetails.getString(3)
                    )
                )
            } while (cursorDetails.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorDetails.close()
        return detailModalArrayList
    }

    // below is the method for updating our courses
    fun updateDetails(
        originalDetailName: String, Name: String?, MailId: String?,
        PhoneNum: String?, Age: String?
    ) {

        // calling a method to get writable database.
        val db = this.writableDatabase
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, Name)
        values.put(AGE_COL, Age)
        values.put(MAILID_COL, MailId)
        values.put(PHONENUM_COL, PhoneNum)

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", arrayOf(originalDetailName))
        db.close()
    }
    fun deleteDetails(Name: String) {

        // on below line we are creating
        // a variable to write our database.
        val db = this.writableDatabase

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "name=?", arrayOf(Name))
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    companion object {
        // creating a constant variables for our database.
        // below variable is for our database name.
        private const val DB_NAME = "details"

        // below int is our database version
        private const val DB_VERSION = 1

        // below variable is for our table name.
        private const val TABLE_NAME = "Details"

        // below variable is for our id column.
        private const val ID_COL = "id"

        // below variable is for our course name column
        private const val NAME_COL = "name"

        // below variable id for our course duration column.
        private const val AGE_COL = "duration"

        // below variable for our course description column.
        private const val MAILID_COL = "description"

        // below variable is for our course tracks column.
        private const val PHONENUM_COL = "tracks"
    }
}

