package co.kr.sopt_seminar_30th.di

import android.app.Application
import android.content.Context
import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
    @Singleton
    @Provides
    @ApplicationContext
    fun provideApplication(application: Application) = application

    @Singleton
    @Provides
    fun provideSopthubDataStore(@ApplicationContext context: Context) = SopthubDataStore(context)
}