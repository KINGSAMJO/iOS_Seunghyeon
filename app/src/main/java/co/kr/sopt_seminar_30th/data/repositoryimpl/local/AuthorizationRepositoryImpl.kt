package co.kr.sopt_seminar_30th.data.repositoryimpl.local

import co.kr.sopt_seminar_30th.data.datasource.local.AuthorizationDao
import co.kr.sopt_seminar_30th.data.model.dto.AuthorizationDto
import co.kr.sopt_seminar_30th.domain.repository.local.AuthorizationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val dao: AuthorizationDao,
    private val coroutineDispatcher: CoroutineDispatcher
) : AuthorizationRepository {
    override suspend fun insertAuthorization(userId: String, autoLogin: Boolean) =
        withContext(coroutineDispatcher) {
            dao.insertAuthorization(AuthorizationDto(userId, autoLogin))
        }

    override suspend fun deleteAuthorization(userId: String, autoLogin: Boolean) =
        withContext(coroutineDispatcher) {
            dao.deleteAuthorization(AuthorizationDto(userId, autoLogin))
        }

    override suspend fun getAuthorization(userId: String): Boolean =
        withContext(coroutineDispatcher) {
            dao.getAuthorization(userId).autoLogin
        }
}