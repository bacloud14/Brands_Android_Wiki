package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.volley.toolbox.NetworkImageView
import com.google.codelabs.mdc.kotlin.shrine.network.ImageRequester
import kotlinx.android.synthetic.main.shr_product_card_fragment.view.*

class ProductCardDetailFragment : Fragment() {
    private lateinit var navigationIconClickListener: NavigationIconClickListener2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        println("onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        println("onCreateView")
        println(MainActivity.currentPosition)
        println(MainActivity.currentProduct.title)

        val view = inflater.inflate(R.layout.shr_product_card_fragment, container, false)
        // Set up the tool bar
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar2)
//        navigationIconClickListener = NavigationIconClickListener2(
//                activity!!,
//                ContextCompat.getDrawable(context!!, R.drawable.shr_branded_menu), // Menu open icon
//                ContextCompat.getDrawable(context!!, R.drawable.shr_close_menu))
//        view.app_bar2.setNavigationOnClickListener(navigationIconClickListener) // Menu close icon
        view.app_bar2.setNavigationOnClickListener {
            (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
        }
        return view /* super.onCreateView(inflater, container, savedInstanceState)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var productImage = getView()?.findViewById<NetworkImageView>(R.id.product_image2)
        ImageRequester.setImageFromUrl(productImage!!, MainActivity.currentProduct.url)
    }
}