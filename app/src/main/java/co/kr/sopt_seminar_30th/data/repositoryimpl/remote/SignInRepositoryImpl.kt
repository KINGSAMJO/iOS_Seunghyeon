package co.kr.sopt_seminar_30th.data.repositoryimpl.remote

import co.kr.sopt_seminar_30th.data.model.request.signin.RequestSignIn
import co.kr.sopt_seminar_30th.data.service.auth.SignInService
import co.kr.sopt_seminar_30th.domain.entity.auth.user.UserInformation
import co.kr.sopt_seminar_30th.domain.repository.remote.SignInRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val service: SignInService,
    private val coroutineDispatcher: CoroutineDispatcher
) : SignInRepository {
    override suspend fun signIn(email: String, password: String): Result<UserInformation> {
        return withContext(coroutineDispatcher) {
            kotlin.runCatching {
                service.postSignIn(RequestSignIn(email, password)).data
            }
        }
    }
}