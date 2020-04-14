package com.dorokhov.openweatherapp.ui.main.weather_main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.RequestManager
import com.dorokhov.openweatherapp.R
import com.dorokhov.openweatherapp.model.WeatherModel
import kotlinx.android.synthetic.main.weather_item_list.view.*

class WeatherListAdapter(
    private val interaction: Interaction? = null,
    private val requestManager: RequestManager
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherModel>() {

        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(
        BlogRecyclerChangeCallBack(this),
        AsyncDifferConfig.Builder(DIFF_CALLBACK).build()
    )

    internal inner class BlogRecyclerChangeCallBack(
        private val adapter: WeatherListAdapter
    ) : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(position, count, payload)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onInserted(position: Int, count: Int) {
            adapter.notifyItemRangeChanged(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyItemRangeRemoved(position, count)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.weather_item_list,
                parent,
                false
            ),
            requestManager = requestManager,
            interaction = interaction
        )

    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    fun submitList(list: List<WeatherModel>?) {
        differ.submitList(list)
    }

    class WeatherViewHolder
    constructor(
        itemView: View,
        val requestManager: RequestManager,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: WeatherModel) = with(itemView) {
            itemView.delete_button.setOnClickListener {
                interaction?.deleteItem(adapterPosition, item)
            }

            itemView.city_name_textView.text = item.city
            itemView.temperature.text =
                itemView.context.getString(R.string.temp_string, item.temperature)
            itemView.description_textView.text = item.description

            requestManager
                .load(item.icon)
                .into(itemView.weather_imageView)

        }
    }

    interface Interaction {
        fun deleteItem(position: Int, item: WeatherModel)
    }
}