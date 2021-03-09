package com.edward.myapplication.entries

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.edward.myapplication.database.Records
import org.w3c.dom.Text

@BindingAdapter("type")
fun TextView.setTypeText(item : Records?){
    item?.let{
        text = item.recordName
    }
}

@BindingAdapter("amount")
fun TextView.setRecordAmount(item : Records?){
    item?.let{
        text = item.recordAmount
    }
}