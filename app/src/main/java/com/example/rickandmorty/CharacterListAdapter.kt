package com.example.rickandmorty

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.models.Result
import kotlinx.android.synthetic.main.item_character_details.view.*


class CharacterListAdapter(
    private val context: Context,
    private val list: List<Result>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_character_details, parent, false)
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyViewHolder) {
            holder.itemView.tv_character_name.text = list[position].name
            Glide
                .with(context)
                .load(list[position].image)
                .centerCrop()
                .into(holder.itemView.iv_character_image)
            holder.itemView.tv_character_species.text = list[position].species
            holder.itemView.tv_character_status.text = list[position].status
            holder.itemView.tv_character_origin.text = list[position].origin.name
            holder.itemView.tv_character_gender.text = list[position].gender

            // TODO change the color of iv_character_status according to the status.
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}