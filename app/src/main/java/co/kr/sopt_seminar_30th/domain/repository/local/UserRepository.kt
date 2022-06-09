package co.kr.sopt_seminar_30th.domain.repository.local

import co.kr.sopt_seminar_30th.domain.entity.tmp.user.UserInfo

interface UserRepository {
    suspend fun getUserInformation(userId: String): UserInfo
    suspend fun login(id: String, password: String): Boolean
    suspend fun insertUserInformation(id: String, password: String, name: String): Long
    suspend fun updateUserInformation(userInformation: UserInfo): UserInfo
    suspend fun getAutoLogin(): Boolean
    suspend fun getUserId(): String
    suspend fun turnOffAutoLogin()
}