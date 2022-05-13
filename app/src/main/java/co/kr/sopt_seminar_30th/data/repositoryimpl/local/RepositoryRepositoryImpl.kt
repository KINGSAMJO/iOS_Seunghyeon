package co.kr.sopt_seminar_30th.data.repositoryimpl.local

import co.kr.sopt_seminar_30th.data.datasource.local.RepositoryDao
import co.kr.sopt_seminar_30th.domain.entity.tmp.repository.RepositoryInformation
import co.kr.sopt_seminar_30th.domain.repository.local.RepositoryRepository
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

    override suspend fun updateRepositoryList(repositoryList: List<RepositoryInformation>) {
        return repositoryDao.updateRepositoryList(repositoryList.map { it.toRepositoryDto() })
    }

    override suspend fun deleteRepository(repository: RepositoryInformation) {
        return repositoryDao.deleteRepository(repository.toRepositoryDto())
    }

    override suspend fun deleteRepositoryList(repositoryList: List<RepositoryInformation>) {
        return repositoryDao.deleteRepositoryList(repositoryList.map { it.toRepositoryDto() })
    }
}