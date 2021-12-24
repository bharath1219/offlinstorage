package com.bharath.offlinestorage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    // creating variables for our edittext, button and dbhandler
    private var NameEdt: EditText? = null
    private var PhonNumEdt: EditText? = null
    private var AgeEdt: EditText? = null
    private var MailIdEdt: EditText? = null
    private var adddetailBtn: FloatingActionButton? = null
    private var readDetailBtn: FloatingActionButton? = null
    private var DetailBtn: FloatingActionButton? = null
    private var addDetail: TextView? = null
    private var viewDetail: TextView? = null
    private var dbHandler: DBHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing all our variables.
        var isAllFabsVisible: Boolean
        NameEdt = findViewById(R.id.idEdtName)
        PhonNumEdt = findViewById(R.id.idEdtPhoneNum)
        AgeEdt = findViewById(R.id.idEdtAge)
        MailIdEdt = findViewById(R.id.idEdtMailID)
        adddetailBtn = findViewById(R.id.idBtnAddCourse)
        readDetailBtn = findViewById(R.id.idBtnReadDetails)
        DetailBtn = findViewById(R.id.add_fab)
        addDetail = findViewById(R.id.add_person_action_text)
        viewDetail = findViewById(R.id.add_alarm_action_text)

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = DBHandler(this@MainActivity)
        readDetailBtn?.setVisibility(View.GONE);
        adddetailBtn?.setVisibility(View.GONE);
        viewDetail?.setVisibility(View.GONE);
        addDetail?.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible = false

        DetailBtn?.setOnClickListener(
            View.OnClickListener {
                isAllFabsVisible = if (!isAllFabsVisible) {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs VISIBLE.
                    readDetailBtn?.show()
                    adddetailBtn?.show()
                    viewDetail?.setVisibility(View.VISIBLE)
                    addDetail?.setVisibility(View.VISIBLE)

                    // make the boolean variable true as
                    // we have set the sub FABs
                    // visibility to GONE
                    true
                } else {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs GONE.
                    adddetailBtn?.hide()
                    readDetailBtn?.hide()
                    viewDetail?.setVisibility(View.GONE)
                    addDetail?.setVisibility(View.GONE)

                    // make the boolean variable false
                    // as we have set the sub FABs
                    // visibility to GONE
                    false
                }
            })
      /*  adddetailBtn?.setOnClickListener(
            View.OnClickListener {
                fun onClick(v: View?) {

                    // below line is to get data from all edit text fields.
                    val Name = NameEdt?.getText().toString()
                    val Age = AgeEdt?.getText().toString()
                    val PhoneNum = PhonNumEdt?.getText().toString()
                    val MailId = MailIdEdt?.getText().toString()

                    // validating if the text fields are empty or not.
                    if (Name.isEmpty() && Age.isEmpty() && PhoneNum.isEmpty() && MailId.isEmpty()) {
                        Toast.makeText(
                            this@MainActivity,
                            "Please enter all the data..",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    // on below line we are calling a method to add new
                    // details to sqlite data and pass all our values to it.
                    dbHandler!!.addNewDetails(Name, Age, MailId, PhoneNum)

                    // after adding the data we are displaying a toast message.
                    Toast.makeText(this@MainActivity, "Details has been added.", Toast.LENGTH_SHORT)
                        .show()
                    NameEdt?.setText("")
                    AgeEdt?.setText("")
                    PhonNumEdt?.setText("")
                    MailIdEdt?.setText("")
                }

            })*/


        // below line is to add on click listener for our add detail button.
        adddetailBtn?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                // below line is to get data from all edit text fields.
                val Name = NameEdt?.getText().toString()
                val Age = AgeEdt?.getText().toString()
                val PhoneNum = PhonNumEdt?.getText().toString()
                val MailId = MailIdEdt?.getText().toString()

                // validating if the text fields are empty or not.
                if (Name.isEmpty() && Age.isEmpty() && PhoneNum.isEmpty() && MailId.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter all the data..",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                // on below line we are calling a method to add new
                // details to sqlite data and pass all our values to it.
                dbHandler!!.addNewDetails(Name, Age, MailId, PhoneNum)

                // after adding the data we are displaying a toast message.
                Toast.makeText(this@MainActivity, "Details has been added.", Toast.LENGTH_SHORT)
                    .show()
                NameEdt?.setText("")
                AgeEdt?.setText("")
                PhonNumEdt?.setText("")
                MailIdEdt?.setText("")
            }
        })
        readDetailBtn?.setOnClickListener(
            View.OnClickListener {
                val i = Intent(this@MainActivity, ViewCourses::class.java)
                startActivity(i)

            })

       /* readDetailBtn?.setOnClickListener({ // opening a new activity via a intent.
            val i = Intent(this@MainActivity, ViewCourses::class.java)
            startActivity(i)
        })*/

    }
}

private fun Button.hide() {

}

private fun Button.show() {

}
