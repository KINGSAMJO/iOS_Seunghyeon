package co.kr.sopt_seminar_30th.data.service.auth

import co.kr.sopt_seminar_30th.data.model.request.signin.SignInRequest
import co.kr.sopt_seminar_30th.data.model.response.BaseResponse
import co.kr.sopt_seminar_30th.domain.entity.auth.user.UserInformation
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("/auth/signin")
    suspend fun postSignIn(
        @Body requestBody: SignInRequest
    ): BaseResponse<UserInformation>
}