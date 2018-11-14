package com.example.maks.city_test.fragments.details.details_frag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maks.city_test.R
import com.example.maks.city_test.json_objects.Data
import kotlinx.android.synthetic.main.details_card.view.*

class RvAdapter : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    var data = listOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.details_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.name.text = data[position].name
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
}