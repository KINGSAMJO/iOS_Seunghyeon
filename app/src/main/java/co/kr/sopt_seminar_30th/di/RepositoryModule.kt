package co.kr.sopt_seminar_30th.di

import co.kr.sopt_seminar_30th.data.datasource.local.UserDao
import co.kr.sopt_seminar_30th.data.repositoryimpl.UserRepositoryImpl
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
        userDao: UserDao
    ): UserRepository = UserRepositoryImpl(userDao)
}