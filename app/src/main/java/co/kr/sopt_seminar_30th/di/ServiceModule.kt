package co.kr.sopt_seminar_30th.di

import co.kr.sopt_seminar_30th.data.service.auth.SignInService
import co.kr.sopt_seminar_30th.data.service.auth.SignUpService
import co.kr.sopt_seminar_30th.data.service.home.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideSignUpService(@SoptRetrofit retrofit: Retrofit): SignUpService {
        return retrofit.create(SignUpService::class.java)
    }

    @Singleton
    @Provides
    fun provideSignInService(@SoptRetrofit retrofit: Retrofit): SignInService {
        return retrofit.create(SignInService::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeService(@GithubRetrofit retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }
}