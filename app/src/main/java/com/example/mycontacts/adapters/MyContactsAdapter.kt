package com.example.mycontacts.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ItemContactViewBinding
import com.example.mycontacts.models.MyContacts

 class MyContactsAdapter<BINDING : ItemContactViewBinding>(var data: ArrayList<MyContacts>?) : RecyclerView.Adapter<MyContactsAdapter.ContactsViewHoler<BINDING>>() {

     private val layoutId: Int = R.layout.item_contact_view




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHoler<BINDING> {
        val binder = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )

        return ContactsViewHoler(binder)
    }

    override fun onBindViewHolder(holder: ContactsViewHoler<BINDING>, position: Int) {

            holder.binder.apply {
                contact =data?.get(position)
                executePendingBindings()
            }
    }

    override fun getItemCount(): Int = data?.size?:0

    class ContactsViewHoler<BINDING : ItemContactViewBinding>(val binder: BINDING) : RecyclerView.ViewHolder(binder.root) {
    }
}