package co.kr.sopt_seminar_30th.di

import co.kr.sopt_seminar_30th.data.datasource.local.FollowerDao
import co.kr.sopt_seminar_30th.data.datasource.local.RepositoryDao
import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.data.datasource.local.UserDao
import co.kr.sopt_seminar_30th.data.repositoryimpl.FollowerRepositoryImpl
import co.kr.sopt_seminar_30th.data.repositoryimpl.RepositoryRepositoryImpl
import co.kr.sopt_seminar_30th.data.repositoryimpl.UserRepositoryImpl
import co.kr.sopt_seminar_30th.domain.repository.FollowerRepository
import co.kr.sopt_seminar_30th.domain.repository.RepositoryRepository
import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao,
        dataStore: SopthubDataStore
    ): UserRepository = UserRepositoryImpl(userDao, dataStore)

    @Singleton
    @Provides
    fun provideFollowerRepository(
        followerDao: FollowerDao
    ): FollowerRepository = FollowerRepositoryImpl(followerDao)

    @Singleton
    @Provides
    fun provideRepositoryRepository(
        repositoryDao: RepositoryDao
    ): RepositoryRepository = RepositoryRepositoryImpl(repositoryDao)
}