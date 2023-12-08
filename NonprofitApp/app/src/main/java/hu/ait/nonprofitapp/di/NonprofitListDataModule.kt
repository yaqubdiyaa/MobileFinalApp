package hu.ait.nonprofitapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.ait.nonprofitapp.data.NonprofitAppDatabase
import hu.ait.nonprofitapp.data.NonprofitDAO
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NonprofitListDataModule {
    @Provides
    fun provideNonprofitDao(appDatabase: NonprofitAppDatabase): NonprofitDAO {
        return appDatabase.nonprofitDao()
    }

    @Provides
    @Singleton
    fun provideShoppingAppDatabase(
        @ApplicationContext appContext: Context): NonprofitAppDatabase {
        return NonprofitAppDatabase.getDatabase(appContext)
    }
}
