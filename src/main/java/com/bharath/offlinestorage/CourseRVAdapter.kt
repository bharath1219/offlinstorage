package com.bharath.offlinestorage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class CourseRVAdapter     // constructor
    (// variable for our array list and context
    private val detailModalArrayList: ArrayList<CourseModal>, private val context: Context
) :
    RecyclerView.Adapter<CourseRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // on below line we are inflating our layout
        // file for our recycler view items.
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.course_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data
        // to our views of recycler view item.
        val modal = detailModalArrayList[position]
        holder.detailNameTV.setText(modal.Name)
        holder.detailPhnNumTV.setText(modal.Age)
        holder.detailMailIdTV.setText(modal.MailId)
        holder.detailAgeTV.setText(modal.phoneNum)

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener { // on below line we are calling an intent.
            val i = Intent(context, UpdateCourseActivity::class.java)

            // below we are passing all our values.
            i.putExtra("name", modal.Name)
            i.putExtra("MailId", modal.MailId)
            i.putExtra("Age", modal.Age)
            i.putExtra("PhoneNUm", modal.phoneNum)

            // starting our activity.
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        // returning the size of our array list
        return detailModalArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our text views.
        val detailNameTV: TextView
        val detailPhnNumTV: TextView
        val detailMailIdTV: TextView
        val detailAgeTV: TextView

        init {
            // initializing our text views
            detailNameTV = itemView.findViewById(R.id.idTVdetailName)
            detailPhnNumTV = itemView.findViewById(R.id.idTVDetailPhonenumber)
            detailMailIdTV = itemView.findViewById(R.id.idTVMailId)
            detailAgeTV = itemView.findViewById(R.id.idTVDetailAge)
        }
    }
}


