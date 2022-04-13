package co.kr.sopt_seminar_30th.domain.usecase.repository

import co.kr.sopt_seminar_30th.di.IoDispatcher
import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation
import co.kr.sopt_seminar_30th.domain.repository.RepositoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRepositoryListUseCase @Inject constructor(
    private val repository: RepositoryRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): List<RepositoryInformation> {
        return withContext(coroutineDispatcher) {
            repository.getRepositoryList()
        }
    }
}