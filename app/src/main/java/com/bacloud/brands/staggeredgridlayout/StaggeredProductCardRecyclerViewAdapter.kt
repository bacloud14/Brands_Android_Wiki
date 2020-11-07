package com.bacloud.brands.staggeredgridlayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bacloud.brands.MainActivity
import com.bacloud.brands.NavigationHost
import com.bacloud.brands.ProductCardDetailFragment
import com.bacloud.brands.network.ImageRequester
import com.bacloud.brands.network.ProductEntry
import com.google.codelabs.mdc.kotlin.shrine.R

/**
 * Adapter used to show an asymmetric grid of products, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
class StaggeredProductCardRecyclerViewAdapter(private val productList: List<ProductEntry>?) : RecyclerView.Adapter<StaggeredProductCardViewHolder>() {

    private var list: List<ProductEntry>? = productList
    override fun getItemViewType(position: Int): Int {
        return position % 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggeredProductCardViewHolder {
        var layoutId = R.layout.shr_staggered_product_card_first
        if (viewType == 1) {
            layoutId = R.layout.shr_staggered_product_card_second
        } else if (viewType == 2) {
            layoutId = R.layout.shr_staggered_product_card_third
        }
        val layoutView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return StaggeredProductCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: StaggeredProductCardViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            var activity: AppCompatActivity = it.context as AppCompatActivity
            MainActivity.currentProduct = list!![position]
            (activity as NavigationHost).navigateTo(ProductCardDetailFragment(), true) // Navigate to the next Fragment
        }
        if (list != null && position < list!!.size) {
            val product = list!![position]
            holder.productTitle.text = product.title.capitalize()
            holder.productPrice.text = product.price
            ImageRequester.setImageFromUrl(holder.productImage, product.url)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    fun replaceList(items: List<ProductEntry>?) {
        list = items
        notifyDataSetChanged()
    }
}
