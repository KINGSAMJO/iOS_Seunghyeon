package co.kr.sopt_seminar_30th.di

import co.kr.sopt_seminar_30th.data.datasource.local.FollowerDao
import co.kr.sopt_seminar_30th.data.datasource.local.RepositoryDao
import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.data.datasource.local.UserDao
import co.kr.sopt_seminar_30th.data.repositoryimpl.local.FollowerRepositoryImpl
import co.kr.sopt_seminar_30th.data.repositoryimpl.local.RepositoryRepositoryImpl
import co.kr.sopt_seminar_30th.data.repositoryimpl.local.UserRepositoryImpl
import co.kr.sopt_seminar_30th.data.repositoryimpl.remote.HomeRepositoryImpl
import co.kr.sopt_seminar_30th.data.repositoryimpl.remote.SignInRepositoryImpl
import co.kr.sopt_seminar_30th.data.repositoryimpl.remote.SignUpRepositoryImpl
import co.kr.sopt_seminar_30th.data.service.auth.SignInService
import co.kr.sopt_seminar_30th.data.service.auth.SignUpService
import co.kr.sopt_seminar_30th.data.service.home.HomeService
import co.kr.sopt_seminar_30th.domain.repository.local.FollowerRepository
import co.kr.sopt_seminar_30th.domain.repository.local.RepositoryRepository
import co.kr.sopt_seminar_30th.domain.repository.local.UserRepository
import co.kr.sopt_seminar_30th.domain.repository.remote.HomeRepository
import co.kr.sopt_seminar_30th.domain.repository.remote.SignInRepository
import co.kr.sopt_seminar_30th.domain.repository.remote.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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

    @Singleton
    @Provides
    fun provideSignUpRepository(
        signUpService: SignUpService,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): SignUpRepository = SignUpRepositoryImpl(signUpService, coroutineDispatcher)

    @Singleton
    @Provides
    fun provideSignInRepository(
        signInService: SignInService,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): SignInRepository = SignInRepositoryImpl(signInService, coroutineDispatcher)

    @Singleton
    @Provides
    fun provideHomeRepository(
        homeService: HomeService,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): HomeRepository = HomeRepositoryImpl(homeService, coroutineDispatcher)
}