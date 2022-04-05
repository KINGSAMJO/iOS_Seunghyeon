package co.kr.sopt_seminar_30th.di

import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import co.kr.sopt_seminar_30th.domain.usecase.user.GetUserInformationUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.InsertUserInformationUseCase
import co.kr.sopt_seminar_30th.domain.usecase.user.UpdateUserInformationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideGetUserInformationUseCase(
        repository: UserRepository
    ): GetUserInformationUseCase {
        return GetUserInformationUseCase(repository)
    }

    @ViewModelScoped
    @Provides
    fun provideInsertUserInformationUseCase(
        repository: UserRepository
    ): InsertUserInformationUseCase {
        return InsertUserInformationUseCase(repository)
    }

    @ViewModelScoped
    @Provides
    fun provideUpdateUserInformationUseCase(
        repository: UserRepository
    ): UpdateUserInformationUseCase {
        return UpdateUserInformationUseCase(repository)
    }
}