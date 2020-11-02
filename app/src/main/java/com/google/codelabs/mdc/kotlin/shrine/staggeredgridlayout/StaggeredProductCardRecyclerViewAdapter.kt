package com.google.codelabs.mdc.kotlin.shrine.staggeredgridlayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.codelabs.mdc.kotlin.shrine.*

import com.google.codelabs.mdc.kotlin.shrine.network.ImageRequester
import com.google.codelabs.mdc.kotlin.shrine.network.ProductEntry

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

        var cardViewHolder = StaggeredProductCardViewHolder(layoutView)

        return cardViewHolder
    }

    override fun onBindViewHolder(holder: StaggeredProductCardViewHolder, position: Int) {
        holder.itemView.setOnClickListener(View.OnClickListener {
            var activity:AppCompatActivity = it.context as AppCompatActivity
            println("position $position")
            MainActivity.position = position
            val product = list!![position]
            println("title")
            MainActivity.title = product.title
            println("title")
            MainActivity.product = product
//            activity.supportFragmentManager.beginTransaction().replace(R.id.product_card_fragment, productCardDetailFragment).addToBackStack(null).commit()
            (activity as NavigationHost).navigateTo(ProductCardDetailFragment(), false) // Navigate to the next Fragment

//            activity.supportFragmentManager.beginTransaction().replace(R.id.product_grid_fragment, productGridFragment).addToBackStack(null).commit()
        })
        if (list != null && position < list!!.size) {
            val product = list!![position]
            holder.productTitle.text = product.title
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
