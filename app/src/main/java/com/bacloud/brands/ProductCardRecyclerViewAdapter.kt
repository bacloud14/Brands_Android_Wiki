package com.bacloud.brands

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bacloud.brands.network.ImageRequester
import com.bacloud.brands.network.ProductEntry
import com.google.codelabs.mdc.kotlin.shrine.R

/**
 * Adapter used to show a simple grid of products.
 */
class ProductCardRecyclerViewAdapter internal constructor(private val productList: List<ProductEntry>) : RecyclerView.Adapter<ProductCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.shr_product_card, parent, false)
        return ProductCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ProductCardViewHolder, position: Int) {
        if (position < productList.size) {
            val product = productList[position]
            holder.productTitle.text = product.title.capitalize()
            holder.productPrice.text = product.price
            ImageRequester.setImageFromUrl(holder.productImage, product.url)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
