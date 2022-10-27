package com.i2c.mcpcc.mycardsv3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ItemContactViewBinding


class MyContactsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_contact_view, parent, false)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {





    }

    override fun getItemCount(): Int {
        return 10
    }

    private inner class ContactsViewHolder(itemView: View) : ViewHolder(itemView) {

    }
}
