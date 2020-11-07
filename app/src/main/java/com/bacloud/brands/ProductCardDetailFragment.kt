package com.bacloud.brands

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.NetworkImageView
import com.bacloud.brands.network.ImageRequester
import com.google.codelabs.mdc.kotlin.shrine.R
import kotlinx.android.synthetic.main.shr_product_card_fragment.view.*

class ProductCardDetailFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        context?.theme?.applyStyle(R.style.Theme_Shrine, true)
        val view = inflater.inflate(R.layout.shr_product_card_fragment, container, false)
        // Set up the tool bar
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar2)
        view.product_title2.text = MainActivity.currentProduct.title.capitalize()
        view.brand.text = MainActivity.currentProduct.title.capitalize()
        view.product_description.text = MainActivity.currentProduct.description
        view.product_description.movementMethod = ScrollingMovementMethod()
        view.app_bar2.setNavigationOnClickListener {
            (activity as NavigationHost).navigateTo(ProductGridFragment(), true)
        }

        return view /* super.onCreateView(inflater, container, savedInstanceState)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var productImage = getView()?.findViewById<NetworkImageView>(R.id.product_image2)
        ImageRequester.setImageFromUrl(productImage!!, MainActivity.currentProduct.url)
    }
}