package co.kr.sopt_seminar_30th.di

import android.content.Context
import co.kr.sopt_seminar_30th.data.database.AppDatabase
import co.kr.sopt_seminar_30th.data.datasource.local.FollowerDao
import co.kr.sopt_seminar_30th.data.datasource.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Singleton
    @Provides
    fun provideFollowerDao(appDatabase: AppDatabase): FollowerDao = appDatabase.followerDao()
}