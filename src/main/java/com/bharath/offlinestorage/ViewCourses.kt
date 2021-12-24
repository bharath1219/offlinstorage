package com.bharath.offlinestorage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class ViewCourses : AppCompatActivity() {
    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private var detailModalArrayList: ArrayList<CourseModal>? = null
    private var dbHandler: DBHandler? = null
    private var detailRVAdapter: CourseRVAdapter? = null
    private var detailsRV: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_courses)

        // initializing our all variables.
        detailModalArrayList = ArrayList()
        dbHandler = DBHandler(this@ViewCourses)

        // getting our course array
        // list from db handler class.
        detailModalArrayList = dbHandler!!.readCourses()

        // on below line passing our array lost to our adapter class.
        detailRVAdapter = CourseRVAdapter(detailModalArrayList!!, this@ViewCourses)
        detailsRV = findViewById(R.id.idRVdetail)

        // setting layout manager for our recycler view.
        val linearLayoutManager =
            LinearLayoutManager(this@ViewCourses, RecyclerView.VERTICAL, false)
        detailsRV?.setLayoutManager(linearLayoutManager)

        // setting our adapter to recycler view.
        detailsRV?.setAdapter(detailRVAdapter)
    }
}

