package co.kr.sopt_seminar_30th.domain.repository.remote

interface SignUpRepository {
    suspend fun signUp(name: String, email: String, password: String): Result<Int>
}