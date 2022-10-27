package com.example.mycontacts.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.databinding.FragmentMyContactsListBinding
import com.example.mycontacts.viewmodels.MyContactsListViewModel
import com.i2c.mcpcc.mycardsv3.adapters.MyContactsAdapter

class MyContactsList : Fragment() {
    lateinit var  _binding :FragmentMyContactsListBinding
    private val binding get() = _binding!!

    private lateinit var viewModel: MyContactsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyContactsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MyContactsListViewModel::class.java]
        val contactsAdapter = MyContactsAdapter()
        binding.rvContactsList.layoutManager =   LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvContactsList.adapter = contactsAdapter

    }

}