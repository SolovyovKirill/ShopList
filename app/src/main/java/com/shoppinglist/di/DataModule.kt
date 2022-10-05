package com.shoppinglist.di

import android.app.Application
import com.shoppinglist.data.AppDatabase
import com.shoppinglist.data.ShopListDao
import com.shoppinglist.data.ShopListRepositoryImpl
import com.shoppinglist.domain.repository.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindShopListRepository(impl: ShopListRepositoryImpl) : ShopListRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideShopListDao(
            application: Application
        ): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}