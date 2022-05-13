package co.kr.sopt_seminar_30th.domain.repository.remote

import co.kr.sopt_seminar_30th.domain.entity.auth.user.UserInformation

interface SignInRepository {
    suspend fun signIn(email: String, password: String): Result<UserInformation>
}