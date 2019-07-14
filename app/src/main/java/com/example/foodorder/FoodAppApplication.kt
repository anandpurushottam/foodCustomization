package com.example.foodorder

import android.app.Application
import android.content.pm.ApplicationInfo
import timber.log.Timber

private var application: FoodAppApplication? = null

class FoodAppApplication : Application() {
    companion object {
        fun getApplcationContext(): FoodAppApplication {
            return application!!
        }

    }

    override fun getApplicationInfo(): ApplicationInfo {
        return super.getApplicationInfo()
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        application=this
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}