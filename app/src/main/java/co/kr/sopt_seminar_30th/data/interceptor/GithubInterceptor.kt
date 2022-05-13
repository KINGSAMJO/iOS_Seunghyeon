package co.kr.sopt_seminar_30th.data.interceptor

import co.kr.sopt_seminar_30th.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val personalAccessToken = BuildConfig.PERSONAL_ACCESS_TOKEN
        val authRequest =
            originalRequest.newBuilder()
                .addHeader("Authorization", "token $personalAccessToken")
                .build()
        val response = chain.proceed(authRequest)
        return response
    }
}