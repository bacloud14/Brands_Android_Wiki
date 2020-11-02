package com.google.codelabs.mdc.kotlin.shrine.staggeredgridlayout

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.NetworkImageView
import com.google.codelabs.mdc.kotlin.shrine.R


class StaggeredProductCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)/*, View.OnClickListener*/ {

    var productImage: NetworkImageView = itemView.findViewById(R.id.product_image)
    var productTitle: TextView = itemView.findViewById(R.id.product_title)
    var productPrice: TextView = itemView.findViewById(R.id.product_price)

//    init{
//        itemView.setOnClickListener(this)
//    }
//
//
//    override fun onClick(v: View?) {
//        println("wow clicked")
////        v?.context?.startActivity(Intent(v.context, ProductCardDetailActivity::class.java))
////        v.findNavController.navigateTo(ProductCardDetailActivity(), false)
//    }
}
