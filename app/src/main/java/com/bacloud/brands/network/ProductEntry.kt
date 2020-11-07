package com.bacloud.brands.network

import android.content.res.Resources
import android.net.Uri
import com.google.codelabs.mdc.kotlin.shrine.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.util.*

/**
 * A product entry in the list of products.
 */
class ProductEntry(
        val title: String, dynamicUrl: String, val url: String, val price: String, val category: String, val description: String) {
    val dynamicUrl: Uri = Uri.parse(dynamicUrl)

    companion object {
        private var target: Int = 0

        /**
         * Loads a raw JSON at R.raw.products and converts it into a list of ProductEntry objects
         */

        fun initProductEntryList(resources: Resources, category: String, query: String, limit: Int, random: Boolean): List<ProductEntry> {
            val inputStream = resources.openRawResource(R.raw.brands_dataset)

            val jsonProductsString = inputStream.bufferedReader().use(BufferedReader::readText)
            val gson = Gson()
            val productListType = object : TypeToken<ArrayList<ProductEntry>>() {}.type
            var list = gson.fromJson<List<ProductEntry>>(jsonProductsString, productListType)
            list = list.filter { it.url != "" }
            list = list.filter { it.description != "" }
            if (query != "all")
                list = list.filter { it.title.startsWith(query, true) }
            if (category != "all")
                list = list.filter { it.category.startsWith(category, true) }
            when (random) {
                true -> Collections.shuffle(list)
            }
            if (limit != 0) {
                list = list.subList(0, list.size.coerceAtMost(limit))
            }
            return list
        }
    }
}