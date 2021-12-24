package com.bharath.offlinestorage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class UpdateCourseActivity : AppCompatActivity() {
    // variables for our edit text, button, strings and dbhandler class.
    private var uptDetailNameEdt: EditText? = null
    private var uptPhnNumEdt: EditText? = null
    private var uptAgeEdt: EditText? = null
    private var uptMailIdEdt: EditText? = null
    private var updateDetailBtn: Button? = null
    private var deleteBtn: Button? = null
    private var dbHandler: DBHandler? = null
    var detailName: String? = null
    var detailMailId: String? = null
    var detailAge: String? = null
    var detailPhnnum: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_course)

        // initializing all our variables.
        uptDetailNameEdt = findViewById(R.id.idUptName)
        uptPhnNumEdt = findViewById(R.id.idUptPhnNum)
        uptAgeEdt = findViewById(R.id.idUptAge)
        uptMailIdEdt = findViewById(R.id.idUptMailId)
        updateDetailBtn = findViewById(R.id.idBtnUpdateDetail)
        deleteBtn = findViewById(R.id.idBtnDelete)

        // on below line we are initialing our dbhandler class.
        dbHandler = DBHandler(this@UpdateCourseActivity)

        // on below lines we are getting data which
        // we passed in our adapter class.
        detailName = intent.getStringExtra("name")
        detailAge = intent.getStringExtra("Age")
        detailMailId = intent.getStringExtra("MailId")
        detailPhnnum = intent.getStringExtra("PhoneNum")


        // setting data to edit text
        // of our update activity.
        uptDetailNameEdt!!.setText(detailName)
        uptMailIdEdt!!.setText(detailMailId)
        uptPhnNumEdt!!.setText(detailAge)
        uptAgeEdt!!.setText(detailPhnnum)

        // adding on click listener to our update course button.
        updateDetailBtn?.setOnClickListener(View.OnClickListener { // inside this method we are calling an update course
            // method and passing all our edit text values.
            dbHandler?.updateDetails(
                detailName!!,
                uptDetailNameEdt!!.getText().toString(),
                uptMailIdEdt!!.getText().toString(),
                uptPhnNumEdt!!.getText().toString(),
                uptAgeEdt!!.getText().toString()

                )

            // displaying a toast message that our course has been updated.
            Toast.makeText(this@UpdateCourseActivity, "Details Updated..", Toast.LENGTH_SHORT).show()

            // launching our main activity.
            val i = Intent(this@UpdateCourseActivity, ViewCourses::class.java)
            startActivity(i)
        })

        deleteBtn?.setOnClickListener(View.OnClickListener { // calling a method to delete our course.
            dbHandler!!.deleteDetails(detailName!!)
            Toast.makeText(this@UpdateCourseActivity, "Detail Deleted", Toast.LENGTH_SHORT)
                .show()
            val i = Intent(this@UpdateCourseActivity, ViewCourses::class.java)
            startActivity(i)
        })
    }
}
