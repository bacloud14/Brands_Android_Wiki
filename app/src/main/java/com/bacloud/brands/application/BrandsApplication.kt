package com.bacloud.brands.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class BrandsApplication : Application() {
    companion object {
        lateinit var instance: BrandsApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}