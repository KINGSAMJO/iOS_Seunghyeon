package co.kr.sopt_seminar_30th.domain.repository

import co.kr.sopt_seminar_30th.domain.entity.user.LoginUserInformation
import co.kr.sopt_seminar_30th.domain.entity.user.SignUpUserInformation
import co.kr.sopt_seminar_30th.domain.entity.user.UserInformation

interface UserRepository {
    suspend fun getUserInformation(userId: String): UserInformation
    suspend fun login(loginUserInformation: LoginUserInformation): Boolean
    suspend fun insertUserInformation(signUpUserInformation: SignUpUserInformation): Long
    suspend fun updateUserInformation(userInformation: UserInformation): UserInformation
    suspend fun getUserId(): String
}