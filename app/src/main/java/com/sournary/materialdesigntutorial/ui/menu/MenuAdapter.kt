package com.sournary.materialdesigntutorial.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.Item
import java.util.concurrent.Executors

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
class MenuAdapter(
    callback: DiffUtil.ItemCallback<Item> = object : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = false

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = false
    }
) : ListAdapter<Item, MenuAdapter.ViewHolder>(
    AsyncDifferConfig
        .Builder<Item>(callback)
        .setBackgroundThreadExecutor(
            Executors.newSingleThreadExecutor()
        ).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listener = object : UserActionsListener {

            override fun onItemClick(v: View, position: Int, item: Item) {
                when (position) {
                    0 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_bottom_appbar)
                    1 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_bottom_nav)
                    2 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_button)
                    3 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_card)
                    4 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_chip)
                    5 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_fab)
                    6 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_font)
                    7 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_tab)
                    8 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_text_field)
                    7 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_tab)
                    9 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_top_appbar)
                    10 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_transformation)
                    11 -> Navigation.findNavController(v).navigate(R.id.action_menu_to_list)
                }
            }
        }
        holder.bindView(getItem(position), listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val thumbnailImage =
            itemView.findViewById<AppCompatImageView>(R.id.image_card_action_thumbnail)
        private val headerText =
            itemView.findViewById<AppCompatTextView>(R.id.text_card_action_header)
        private val subHeadText = itemView.findViewById<AppCompatTextView>(R.id.text_subhead)
        private val mediaImage = itemView.findViewById<AppCompatImageView>(R.id.image_media)
        private val supportText = itemView.findViewById<AppCompatTextView>(R.id.text_support)
        private val viewDetailButton =
            itemView.findViewById<MaterialButton>(R.id.button_view_detail)

        fun bindView(item: Item, listener: UserActionsListener) {
            thumbnailImage.setImageResource(item.thumbnail)
            headerText.text = item.header
            subHeadText.text = item.subhead
            mediaImage.setImageResource(item.media)
            supportText.text = item.supportText
            viewDetailButton.setOnClickListener { listener.onItemClick(it, adapterPosition, item) }
        }
    }
}
