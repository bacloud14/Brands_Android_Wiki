package com.google.codelabs.mdc.kotlin.shrine

import android.animation.AnimatorSet
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.google.codelabs.mdc.kotlin.shrine.network.ProductEntry

/**
 * [android.view.View.OnClickListener] used to translate the product grid sheet downward on
 * the Y-axis when the navigation icon in the toolbar is pressed.
 */
class NavigationIconClickListener2 @JvmOverloads internal constructor(
        private val context: Context,
        private val openIcon: Drawable? = null, private val closeIcon: Drawable? = null) : View.OnClickListener {

    private val animatorSet = AnimatorSet()
    private var backdropShown = false
    private lateinit var theList: List<ProductEntry>


    override fun onClick(view: View) {
        backdropShown = !backdropShown
        updateIcon(view)
    }

    private fun updateIcon(view: View) {
        if (openIcon != null && closeIcon != null) {
            if (view !is ImageView) {
                theList = ProductEntry.initProductEntryList(ProductGridFragment.productGridFragmentResources, category = "food_and_beverage", query = "all", limit = 0, random = false)
                ProductGridFragment.adapter.replaceList(theList)

                return
//                throw IllegalArgumentException("updateIcon() must be called on an ImageView")
            }
            if (backdropShown) {
                view.setImageDrawable(closeIcon)
            } else {
                view.setImageDrawable(openIcon)
            }
        }
    }
}
