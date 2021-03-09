package com.edward.myapplication.entries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.edward.myapplication.database.Records
import com.edward.myapplication.databinding.ListItemViewBinding

class EntryAdapter:androidx.recyclerview.widget.ListAdapter<Records,
        EntryAdapter.ViewHolder>(EntryDiffCallBack()){



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    /*
Note, we will create a viewholder class here so that it will be used by our
oncreate viewholder
 */
    class ViewHolder private constructor(val binding: ListItemViewBinding): RecyclerView.ViewHolder(binding.root){
    // this method uses bindingadapter annotation from bindingutils
        fun bind(
            item: Records
        ) {
            binding.record = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class EntryDiffCallBack: DiffUtil.ItemCallback<Records>(){
    override fun areItemsTheSame(oldItem: Records, newItem: Records): Boolean {
        return oldItem.recordId == newItem.recordId
    }

    override fun areContentsTheSame(oldItem: Records, newItem: Records): Boolean {
        return oldItem == newItem
    }

}

