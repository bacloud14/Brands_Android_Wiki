package com.bacloud.brands

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bacloud.brands.network.ProductEntry
import com.bacloud.brands.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter
import com.google.codelabs.mdc.kotlin.shrine.R
import kotlinx.android.synthetic.main.shr_product_grid_fragment.view.*


class ProductGridFragment : Fragment() {

    companion object {
        lateinit var productGridFragmentResources: Resources
        lateinit var adapter: StaggeredProductCardRecyclerViewAdapter
    }

    private var isSearch: Boolean = false
    private lateinit var navigationIconClickListener: NavigationIconClickListener
    private lateinit var theList: List<ProductEntry>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productGridFragmentResources = resources
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false)

        // Set up the tool bar
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        navigationIconClickListener = NavigationIconClickListener(
                activity!!,
                view.product_grid,
                view.app_bar,
                AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(context!!, R.drawable.shr_branded_menu), // Menu open icon
                ContextCompat.getDrawable(context!!, R.drawable.shr_close_menu)) // Menu close icon
        view.app_bar.setNavigationOnClickListener(navigationIconClickListener)

        // Set up the RecyclerView
        view.recycler_view.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }
        view.recycler_view.layoutManager = gridLayoutManager

        theList = ProductEntry.initProductEntryList(resources, category = "all", query = "all", limit = 30, random = true)
        adapter = StaggeredProductCardRecyclerViewAdapter(
                theList)
        view.recycler_view.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small)
        view.recycler_view.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))

        // Set cut corner background for API 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.product_grid.background = context?.getDrawable(R.drawable.shr_product_grid_background_shape)
        }

        attachChangesProcessor(view)

        return view
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val search = menu.findItem(R.id.search)
        search.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem?): Boolean {
                isSearch = true
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem?): Boolean {
                isSearch = false
                return true
            }
        })
        var vwSearch = search.actionView as SearchView
        vwSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                println(query)
                theList = ProductEntry.initProductEntryList(resources, category = "all", query = query, limit = 10, random = false)
                adapter.replaceList(theList)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                theList = ProductEntry.initProductEntryList(resources, category = "all", query = newText, limit = 10, random = false)
                adapter.replaceList(theList)
                return true
            }
        })

        vwSearch.setOnCloseListener { //Do something on collapse Searchview
            false
        }

    }


    private fun attachChangesProcessor(view: View) {
        val featured = view.findViewById(R.id.featured) as Button
        featured.setOnClickListener(navigationIconClickListener)
        featured.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shr_featured, 0, 0, 0)
        val beautyAndCosmetics = view.findViewById(R.id.beauty_and_cosmetics) as Button
        beautyAndCosmetics.setOnClickListener(navigationIconClickListener)
        beautyAndCosmetics.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shr_beauty_and_cosmetics, 0, 0, 0)
        val cars = view.findViewById(R.id.cars) as Button
        cars.setOnClickListener(navigationIconClickListener)
        cars.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shr_cars, 0, 0, 0)
        val fashionAndClothing = view.findViewById(R.id.fashion_and_clothing) as Button
        fashionAndClothing.setOnClickListener(navigationIconClickListener)
        fashionAndClothing.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shr_fashion, 0, 0, 0)
        val foodAndBeverage = view.findViewById(R.id.food_and_beverage) as Button
        foodAndBeverage.setOnClickListener(navigationIconClickListener)
        foodAndBeverage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shr_food_and_beverages, 0, 0, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val nCurrentOrientation: Int = getScreenOrientation()
    }

    private fun getScreenOrientation(): Int {
        return resources.configuration.orientation
    }

}