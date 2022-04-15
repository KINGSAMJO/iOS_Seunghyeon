package co.kr.sopt_seminar_30th.data.repositoryimpl

import co.kr.sopt_seminar_30th.data.datasource.local.RepositoryDao
import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation
import co.kr.sopt_seminar_30th.domain.repository.RepositoryRepository
import co.kr.sopt_seminar_30th.util.toRepositoryDto
import javax.inject.Inject

class RepositoryRepositoryImpl @Inject constructor(
    private val repositoryDao: RepositoryDao
) : RepositoryRepository {
    override suspend fun insertRepositoryList(repositoryList: List<RepositoryInformation>): List<Long> {
        return repositoryDao.insertRepositoryList(
            repositoryList.map { it.toRepositoryDto() }
        )
    }

    override suspend fun getRepositoryList(): List<RepositoryInformation> {
        return repositoryDao.getRepositoryList().map { it.toRepositoryInformation() }
    }
}