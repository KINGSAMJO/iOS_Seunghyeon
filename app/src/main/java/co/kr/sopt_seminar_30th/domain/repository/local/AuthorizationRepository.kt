package co.kr.sopt_seminar_30th.domain.repository.local

interface AuthorizationRepository {
    suspend fun insertAuthorization(userId: String, autoLogin: Boolean)
    suspend fun deleteAuthorization(userId: String, autoLogin: Boolean)
    suspend fun getAuthorization(userId: String): Boolean
}