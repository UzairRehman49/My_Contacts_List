package com.example.mycontacts.repositories

import DBHandler
import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import com.example.mycontacts.fragments.MyContactsList
import com.example.mycontacts.models.MyContacts
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyContactsRepository @Inject constructor(@ApplicationContext val context: Context)  {

     val myContactsLiveData = MutableLiveData<ArrayList<MyContacts>?>()

    suspend fun getContactList(){
        val myContacts = ArrayList<MyContacts>()

        withContext(Dispatchers.IO)
        {
            val cr: ContentResolver = context.contentResolver
            var countt =0
            val cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
            if (cur?.count ?: 0 > 0) {
                while (cur != null && cur.moveToNext() && countt <10) {
                    countt++
                    val id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
                    val name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    var phone :String? = ""
                    var email :String? = ""
                    var photo :String? = " "

                    if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        val pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf(id), null)

                        while (pCur!!.moveToNext()) {
                            val phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            val photoUri = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_THUMBNAIL_URI))
                            phone = phoneNo
                            photo = photoUri
                        }
                        pCur.close()
                    }
                    val eCur =cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", arrayOf(id),null)
                    while (null!=eCur && eCur.moveToNext())
                    {
                        val emailAdr = eCur.getString(eCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS))
                        email = emailAdr

                    }
                    eCur?.close()

                    myContacts.add(MyContacts(name,phone,email,photo))
                }
            }
            cur?.close()
        }
        if(!myContacts.isNullOrEmpty())
            myContactsLiveData.postValue(myContacts)
        else
            myContactsLiveData.postValue(null)
    }
    suspend fun writeToDB(contactsList:ArrayList<MyContacts>)
    {
        val dbHandler  = DBHandler(context)

        withContext(Dispatchers.IO)
        {
            if(!contactsList.isNullOrEmpty())
            {
                for (contact in contactsList)
                {
                    dbHandler.addNewContact(contact)
                }
            }

        }
    }



}

