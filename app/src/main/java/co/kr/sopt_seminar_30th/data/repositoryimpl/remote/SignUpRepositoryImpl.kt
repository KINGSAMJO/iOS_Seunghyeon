package co.kr.sopt_seminar_30th.data.repositoryimpl.remote

import co.kr.sopt_seminar_30th.data.model.request.signup.SignUpRequest
import co.kr.sopt_seminar_30th.data.service.auth.SignUpService
import co.kr.sopt_seminar_30th.domain.repository.remote.SignUpRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val service: SignUpService,
    private val coroutineDispatcher: CoroutineDispatcher
) : SignUpRepository {
    override suspend fun signUp(name: String, email: String, password: String): Result<Int> =
        withContext(coroutineDispatcher) {
            kotlin.runCatching {
                service.postSignUp(SignUpRequest(name, email, password)).data.id
            }
        }
}