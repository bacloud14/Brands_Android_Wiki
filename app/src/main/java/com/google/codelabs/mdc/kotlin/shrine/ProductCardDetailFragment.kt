package com.google.codelabs.mdc.kotlin.shrine

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.NetworkImageView
import com.google.codelabs.mdc.kotlin.shrine.network.ImageRequester
import kotlinx.android.synthetic.main.shr_product_grid_fragment.view.*

class ProductCardDetailFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        println("onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        println("onCreateView")
        println(MainActivity.position)
        println("title")
        println(MainActivity.title)
        println("product")
        println(MainActivity.product)

        val view = inflater.inflate(R.layout.shr_product_card_fragment, container, false)
        // Set up the tool bar
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        view.app_bar.setOnClickListener(View.OnClickListener {
            (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
        })
        return view /* super.onCreateView(inflater, container, savedInstanceState)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var productImage  = getView()?.findViewById<NetworkImageView>(R.id.product_image2)
        ImageRequester.setImageFromUrl(productImage!!, MainActivity.url)
    }
}