package com.sournary.materialdesigntutorial.ui.list

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
import com.sournary.materialdesigntutorial.model.Product
import java.util.concurrent.Executors

/**
 * Created: 11/09/2018
 * By: Sang
 * Description:
 */
class ProductAdapter(
    private val itemListener: ProductUserActionsListener,
    itemCallback: DiffUtil.ItemCallback<Product> = object : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.name == newItem.name
    }
) : ListAdapter<Product, ProductAdapter.ViewHolder>(
    AsyncDifferConfig.Builder<Product>(itemCallback)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position), itemListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val avatarImage =
            itemView.findViewById<AppCompatImageView>(R.id.image_supporting_visual)
        private val nameText = itemView.findViewById<AppCompatTextView>(R.id.text_name)
        private val locationText = itemView.findViewById<AppCompatTextView>(R.id.text_location)
        private val priceText = itemView.findViewById<AppCompatTextView>(R.id.text_price)

        fun bindView(product: Product, listener: ProductUserActionsListener) {
            avatarImage.setImageResource(product.imageUrl)
            nameText.text = product.name
            locationText.text = product.location
            priceText.text = product.money
            itemView.setOnClickListener { listener.onItemClick(it, product, adapterPosition) }
        }
    }
}