package com.demo.myapplicationnotes.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.myapplicationnotes.R

class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    val title: TextView = itemView.findViewById(R.id.titleTextView)
    val description: TextView = itemView.findViewById(R.id.descriptionTextView)
    var deleteButton:ImageView = itemView.findViewById(R.id.deleteButton)

}

