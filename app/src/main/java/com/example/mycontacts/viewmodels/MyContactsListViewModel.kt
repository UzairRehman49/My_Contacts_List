package com.example.mycontacts.viewmodels

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontacts.R
import com.example.mycontacts.fragments.MyContactsList
import com.example.mycontacts.models.MyContacts
import com.example.mycontacts.repositories.MyContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyContactsListViewModel @Inject constructor(private val contactsRepository: MyContactsRepository) : ViewModel() {
     val myContact =contactsRepository.myContactsLiveData

    fun getContacts( ) {
        viewModelScope.launch {
            contactsRepository.getContactList()
        }
    }
    fun writeToDB(contactsList: ArrayList<MyContacts>)
    {
        viewModelScope.launch {
            contactsRepository.writeToDB(contactsList)
        }
    }



}
@BindingAdapter("imageUri")
fun loadImage(view: ImageView, uri: String?) {

    if (null != uri && uri.isNotEmpty())
        view.setImageURI(Uri.parse(uri))
    }