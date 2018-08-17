package com.sournary.materialdesigntutorial.ui.card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.data.Weather
import java.util.concurrent.Executors

/**
 * Created: 20/08/2018
 * By: Sang
 * Description:
 */
class WeatherAdapter(
    callback: DiffUtil.ItemCallback<Weather> = object : DiffUtil.ItemCallback<Weather>() {

        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean =
            oldItem.day == newItem.day
    }
) : ListAdapter<Weather, WeatherAdapter.ViewHolder>(
    AsyncDifferConfig.Builder<Weather>(callback)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dayText: AppCompatTextView = itemView.findViewById(R.id.text_day)
        private val predictCelsiusText: AppCompatTextView =
            itemView.findViewById(R.id.text_celsius_predict)
        private val actualCelsiusText: AppCompatTextView =
            itemView.findViewById(R.id.text_celsius_actual)
        private val avatarImage: AppCompatImageView = itemView.findViewById(R.id.image_avatar)

        fun bindView(weather: Weather) {
            dayText.text = weather.day
            predictCelsiusText.text = weather.predictCelsius
            actualCelsiusText.text = weather.actualCelsius
            avatarImage.setImageResource(weather.avatar)
        }
    }
}