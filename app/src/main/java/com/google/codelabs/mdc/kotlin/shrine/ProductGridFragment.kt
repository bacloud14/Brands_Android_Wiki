package com.google.codelabs.mdc.kotlin.shrine

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.codelabs.mdc.kotlin.shrine.network.ProductEntry
import com.google.codelabs.mdc.kotlin.shrine.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter
import kotlinx.android.synthetic.main.shr_product_grid_fragment.view.*

class ProductGridFragment : Fragment() {

    companion object {
        lateinit var productGridFragmentResources: Resources
        lateinit var adapter: StaggeredProductCardRecyclerViewAdapter
    }

    private lateinit var navigationIconClickListener: NavigationIconClickListener
    private lateinit var theList: List<ProductEntry>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productGridFragmentResources = resources
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment with the ProductGrid theme
        val view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false)

        // Set up the tool bar
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        navigationIconClickListener = NavigationIconClickListener(
                activity!!,
                view.product_grid,
                AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(context!!, R.drawable.shr_branded_menu), // Menu open icon
                ContextCompat.getDrawable(context!!, R.drawable.shr_close_menu))
        view.app_bar.setNavigationOnClickListener(navigationIconClickListener) // Menu close icon

        // Set up the RecyclerView
        view.recycler_view.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }
        view.recycler_view.layoutManager = gridLayoutManager

        theList = ProductEntry.initProductEntryList(resources, s = "all", limit = 15, random = true)
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

    private fun attachChangesProcessor(view: View) {
        // get reference to button
        val featured = view.findViewById(R.id.featured) as Button

        // set on-click listener
        featured.setOnClickListener(navigationIconClickListener)
//        featured.setOnClickListener {
////            val displayMetrics = DisplayMetrics()
////            (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
////            var height = displayMetrics.heightPixels
////            val animatorSet = AnimatorSet()
////            animatorSet.removeAllListeners()
////            animatorSet.end()
////            animatorSet.cancel()
////            val translateY = height - this.resources.getDimensionPixelSize(R.dimen.shr_product_grid_reveal_height)
////            val animator = ObjectAnimator.ofFloat(view.app_bar, "translationY", 0.toFloat())
////            animator.duration = 500
////            val interpolator: Interpolator? = null
////            if (interpolator != null) {
////                animator.interpolator = interpolator
////            }
////            animatorSet.play(animator)
////            animator.start()
//            theList = ProductEntry.initProductEntryList(resources, s = "featured", limit = 0, random = false)
//            this.adapter.replaceList(theList)
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val nCurrentOrientation: Int = getScreenOrientation()

        if (nCurrentOrientation == 2) {
            this.view!!.app_bar.setNavigationOnClickListener(NavigationIconClickListener(
                    activity!!,
                    this.view!!.product_grid,
                    AccelerateDecelerateInterpolator(),
                    ContextCompat.getDrawable(context!!, R.drawable.shr_branded_menu), // Menu open icon
                    ContextCompat.getDrawable(context!!, R.drawable.shr_close_menu))) // Menu close icon
        }
    }

    private fun getScreenOrientation(): Int {
        return resources.configuration.orientation
    }

}