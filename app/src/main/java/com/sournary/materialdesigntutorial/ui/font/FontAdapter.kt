package com.sournary.materialdesigntutorial.ui.font

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.data.Font
import java.util.concurrent.Executors

/**
 * Created: 24/08/2018
 * By: Sang
 * Description:
 */
class FontAdapter(
    private val context: Context,
    callback: DiffUtil.ItemCallback<Font> = object : DiffUtil.ItemCallback<Font>() {

        override fun areItemsTheSame(oldItem: Font, newItem: Font): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Font, newItem: Font): Boolean =
            oldItem.name == newItem.name
    }
) : ListAdapter<Font, FontAdapter.ViewHolder>(
    AsyncDifferConfig.Builder<Font>(callback)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_font, parent, false)
        return ViewHolder(context, rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    class ViewHolder(private val context: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val headerText = itemView.findViewById<AppCompatTextView>(R.id.text_header)
        private val subheadText = itemView.findViewById<AppCompatTextView>(R.id.text_subhead)
        private val demoText = itemView.findViewById<AppCompatTextView>(R.id.text_demo)

        fun bindView(font: Font) {
            headerText.text = font.name
            subheadText.text = font.describe
            demoText.text = font.demo

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                demoText.setTextAppearance(font.style)
            } else {
                demoText.setTextAppearance(context, font.style)
            }
        }
    }
}