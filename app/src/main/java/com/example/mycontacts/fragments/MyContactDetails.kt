package com.example.mycontacts.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycontacts.R
import com.example.mycontacts.viewmodels.MyContactDetailsViewModel

class MyContactDetails : Fragment() {

    companion object {
        fun newInstance() = MyContactDetails()
    }

    private lateinit var viewModel: MyContactDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_contact_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyContactDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}