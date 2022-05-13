package co.kr.sopt_seminar_30th.di

import android.app.Application
import android.content.Context
import co.kr.sopt_seminar_30th.BuildConfig
import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.data.interceptor.GithubInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
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

    @Singleton
    @Provides
    fun provideGithubInterceptor(): Interceptor = GithubInterceptor()

    @SoptClient
    @Singleton
    @Provides
    fun provideOkHttpInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @GithubClient
    @Singleton
    @Provides
    fun provideGithubOkHttpInterceptor(
        githubInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(githubInterceptor)
            .build()
    }

    @SoptRetrofit
    @Singleton
    @Provides
    fun provideSoptRetrofit(
        @SoptClient client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @GithubRetrofit
    @Singleton
    @Provides
    fun provideGithubRetrofit(
        @GithubClient client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SoptClient

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class GithubClient

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SoptRetrofit

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class GithubRetrofit