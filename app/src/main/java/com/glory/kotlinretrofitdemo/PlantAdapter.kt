package com.glory.kotlinretrofitdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.glory.kotlinretrofitdemo.databinding.ActivityMainBinding
import com.glory.kotlinretrofitdemo.model.PlantDTO
import kotlinx.android.synthetic.main.plant_list_items.view.*

class PlantAdapter(private val context: Context) :
    ListAdapter<PlantDTO, PlantAdapter.ItemViewholder>(PlantCallBack()) {

    lateinit var binding: ActivityMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.plant_list_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        val plantList = getItem(position)
        holder.bind(listOf(plantList), context)
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val aokRecyclerView = itemView.findViewById<RecyclerView>(R.id.oak_recyclerview)
        fun bind(plant: List<PlantDTO>, context: Context) = with(itemView) {

            //Implement the child recyclerview

            val oakPlantAdapter = ChildOakPlantAdapter(context)
            val layoutManager = LinearLayoutManager(context,
                   LinearLayoutManager.HORIZONTAL, false)
                aokRecyclerView.layoutManager = layoutManager
                aokRecyclerView.setHasFixedSize(true)
                aokRecyclerView.adapter = oakPlantAdapter
                oakPlantAdapter.submitList(plant)

           /* aokRecyclerView.apply {
                val oakPlantAdapter = ChildOakPlantAdapter(context)
                layoutManager = LinearLayoutManager(context)
                this@ItemViewholder.aokRecyclerView.setHasFixedSize(true)
                aokRecyclerView.adapter = oakPlantAdapter
                oakPlantAdapter.submitList(plant)
            }*/

            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}

class PlantCallBack : DiffUtil.ItemCallback<PlantDTO>() {
    override fun areItemsTheSame(oldItem: PlantDTO, newItem: PlantDTO): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: PlantDTO, newItem: PlantDTO): Boolean {
        return oldItem == newItem
    }
}