package com.shoppinglist.di

import android.app.Application
import com.shoppinglist.data.ShopListProvider
import com.shoppinglist.presentation.screen.mainactivity.MainActivity
import com.shoppinglist.presentation.screen.shopitemfragment.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: ShopItemFragment)

    fun inject(provider: ShopListProvider)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ) : ApplicationComponent
    }


}