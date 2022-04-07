package co.kr.sopt_seminar_30th.di

import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import co.kr.sopt_seminar_30th.domain.usecase.user.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideGetUserInformationUseCase(
        repository: UserRepository,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): GetUserInformationUseCase {
        return GetUserInformationUseCase(repository, coroutineDispatcher)
    }

    @ViewModelScoped
    @Provides
    fun provideInsertUserInformationUseCase(
        repository: UserRepository,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): InsertUserInformationUseCase {
        return InsertUserInformationUseCase(repository, coroutineDispatcher)
    }

    @ViewModelScoped
    @Provides
    fun provideUpdateUserInformationUseCase(
        repository: UserRepository,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): UpdateUserInformationUseCase {
        return UpdateUserInformationUseCase(repository, coroutineDispatcher)
    }

    @ViewModelScoped
    @Provides
    fun provideGetPreferenceUserIdUseCase(
        repository: UserRepository,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): GetUserIdUseCase {
        return GetUserIdUseCase(repository, coroutineDispatcher)
    }

    @ViewModelScoped
    @Provides
    fun provideLoginUseCase(
        repository: UserRepository,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): LoginUseCase {
        return LoginUseCase(repository, coroutineDispatcher)
    }
}