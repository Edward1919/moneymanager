package com.edward.myapplication.entries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.edward.myapplication.R
import com.edward.myapplication.database.RecordsDatabase
import com.edward.myapplication.databinding.FragmentEntriesBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EntriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EntriesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout via binding
        val binding = DataBindingUtil.inflate<FragmentEntriesBinding>(
            inflater, R.layout.fragment_entries, container, false)
        val application = requireNotNull(this.activity).application

        val dataSource = RecordsDatabase.getInstance(application).recordsDatabaseDAO

        val viewModelFactory = EntryViewModelFactory(dataSource, application)

        val entryViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(EntryViewModel::class.java)

        binding.entryViewModel = entryViewModel

        //binding.setLifecycleOwner(this)
        binding.lifecycleOwner = this


        val adapter = EntryAdapter()
        binding.recordsList.adapter = adapter

        //this will update data on our view when data changes in database
        entryViewModel.records.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })


        binding.button.setOnClickListener{
            view: View ->
            view.findNavController().navigate(R.id.action_records_to_expenseFragment)
        }



        return binding.root

    }

}