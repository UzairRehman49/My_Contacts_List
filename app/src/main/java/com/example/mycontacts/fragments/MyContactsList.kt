package com.example.mycontacts.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mycontacts.adapters.MyContactsAdapter
import com.example.mycontacts.databinding.FragmentMyContactsListBinding
import com.example.mycontacts.databinding.ItemContactViewBinding
import com.example.mycontacts.models.MyContacts
import com.example.mycontacts.viewmodels.MyContactsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyContactsList : Fragment() {
    private var binding: FragmentMyContactsListBinding? = null

    private lateinit var viewModel: MyContactsListViewModel
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.viewModelScope.launch {
                viewModel.getContacts()
            }
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentMyContactsListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MyContactsListViewModel::class.java]
        askForPermission()
        viewModel.myContact.observe(viewLifecycleOwner, Observer {
            if(null ==it)
            {
                Log.DEBUG
            }
            else
            {
                binding?.contactsAdapter = MyContactsAdapter<ItemContactViewBinding>(it)
                viewModel.viewModelScope.launch {

                        viewModel.writeToDB(it)

                }
            }


        })

    }
    fun askForPermission()
    {

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) -> {

                viewModel.viewModelScope.launch {
                    viewModel.getContacts()
                }

            }
            else -> {

                requestPermissionLauncher.launch(
                    Manifest.permission.READ_CONTACTS)
            }
        }
    }







}