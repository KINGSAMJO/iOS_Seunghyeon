package co.kr.sopt_seminar_30th.data.service.auth

import co.kr.sopt_seminar_30th.data.model.request.signup.RequestSignUp
import co.kr.sopt_seminar_30th.data.model.response.BaseResponse
import co.kr.sopt_seminar_30th.data.model.response.signup.ResponseSignUp
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("/auth/signup")
    suspend fun postSignUp(
        @Body requestBody: RequestSignUp
    ): BaseResponse<ResponseSignUp>
}