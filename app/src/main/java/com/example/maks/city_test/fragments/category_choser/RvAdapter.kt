package com.example.maks.city_test.fragments.category_choser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maks.city_test.Categories
import com.example.maks.city_test.R
import kotlinx.android.synthetic.main.category_card.view.*

interface OnCardClickListener{
    fun onCardClick(id:Int)
}

class RvAdapter : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_card, parent, false)
        return ViewHolder(v)
    }

    lateinit var onCardClickListener:OnCardClickListener
    override fun getItemCount(): Int = 11

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            Categories.RESTAURANTS.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_restaurant_black_24dp)
                holder.itemView.description.text = Categories.RESTAURANTS.name
            }
            Categories.ENTERTAINMENT.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_stars_black_24dp)
                holder.itemView.description.text = Categories.ENTERTAINMENT.name
            }
            Categories.SHOPS.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_shopping_cart_black_24dp)
                holder.itemView.description.text = Categories.SHOPS.name
            }

            Categories.SPORT.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_fitness_center_black_24dp)
                holder.itemView.description.text = Categories.SPORT.name
            }

            Categories.MEDICINE.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_local_hospital_black_24dp)
                holder.itemView.description.text = Categories.MEDICINE.name
            }

            Categories.CLUBS.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_cake_black_24dp)
                holder.itemView.description.text = Categories.CLUBS.name
            }
            Categories.BEAUTY.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_spa_black_24dp)
                holder.itemView.description.text = Categories.BEAUTY.name
            }

            Categories.HOTEL.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_hotel_black_24dp)
                holder.itemView.description.text = Categories.HOTEL.name
            }

            Categories.AUTO.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_directions_car_black_24dp)
                holder.itemView.description.text = Categories.AUTO.name
            }

            Categories.ANIMALS.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_pets_black_24dp)
                holder.itemView.description.text = Categories.ANIMALS.name
            }
            Categories.SERVICES.ordinal -> {
                holder.itemView.image.setImageResource(R.drawable.ic_person_outline_black_24dp)
                holder.itemView.description.text = Categories.SERVICES.name
            }


        }

        holder.itemView.card.setOnClickListener {
            onCardClickListener.onCardClick(position)
        }
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
}