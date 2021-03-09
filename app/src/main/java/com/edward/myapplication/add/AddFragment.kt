package com.edward.myapplication.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.edward.myapplication.R
import com.edward.myapplication.database.Records
import com.edward.myapplication.database.RecordsDatabase
import com.edward.myapplication.databinding.FragmentAddBinding

class ExpenseFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout via binding
        binding = DataBindingUtil.inflate<FragmentAddBinding>(
            inflater, R.layout.fragment_add, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = RecordsDatabase.getInstance(application).recordsDatabaseDAO
        val viewModelFactory = AddViewModelFactory(dataSource,application)
        val addViewModel = ViewModelProvider(this,viewModelFactory).get(
            AddViewModel::class.java
        )

        binding.addViewModel = addViewModel
        binding.setLifecycleOwner(this)


        binding.saveButton.setOnClickListener{
                view: View ->
            view.findNavController().navigate(R.id.action_expenseFragment_to_records)
            addRecord()
        }

        return binding.root

    }

    private fun addRecord(){
        val type = binding.edittextType.text.toString()
        val amount = binding.amount.text.toString()
        val note = binding.edittextNote.text.toString()

        val record = Records(0,type, amount, false,0,0,note)
        binding.addViewModel?.addEntry(record)


    }



}