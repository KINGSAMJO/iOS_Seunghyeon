package co.kr.sopt_seminar_30th.domain.usecase.repository

import co.kr.sopt_seminar_30th.di.IoDispatcher
import co.kr.sopt_seminar_30th.domain.entity.tmp.repository.RepositoryInformation
import co.kr.sopt_seminar_30th.domain.repository.local.RepositoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteRepositoryListUseCase @Inject constructor(
    private val repository: RepositoryRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(repositoryList: List<RepositoryInformation>) {
        withContext(coroutineDispatcher) {
            repository.deleteRepositoryList(repositoryList)
        }
    }
}