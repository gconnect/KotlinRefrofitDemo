package com.glory.kotlinretrofitdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.glory.kotlinretrofitdemo.model.PlantDTO
import kotlinx.android.synthetic.main.plant_list_items.view.*

class ChildOakPlantAdapter(private val context: Context) :
    ListAdapter<PlantDTO, ChildOakPlantAdapter.ItemViewholder>(OakDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.plant_list_items, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ChildOakPlantAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(plant: PlantDTO) = with(itemView) {

            this.id_textView.text = plant.plantId.toString()
            this.genus_textView.text = plant.genus
            this.species_textView.text = plant.species
            this.cultivar_textView.text = plant.cultivar
            this.common_textView.text = plant.common
            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}

class OakDiffCallBack : DiffUtil.ItemCallback<PlantDTO>() {
    override fun areItemsTheSame(oldItem: PlantDTO, newItem: PlantDTO): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: PlantDTO, newItem: PlantDTO): Boolean {
        return oldItem == newItem
    }
}