package co.kr.sopt_seminar_30th.domain.repository

import co.kr.sopt_seminar_30th.domain.entity.user.UserInformation

interface UserRepository {
    suspend fun getUserInformation(userId: String): UserInformation
    suspend fun login(id: String, password: String): Boolean
    suspend fun insertUserInformation(id: String, password: String, name: String): Long
    suspend fun updateUserInformation(userInformation: UserInformation): UserInformation
    suspend fun getAutoLogin(): Boolean
    suspend fun getUserId(): String
}