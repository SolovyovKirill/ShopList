package com.shoppinglist

import android.app.Application
import com.shoppinglist.di.DaggerApplicationComponent

class ShopListApp : Application(){

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}