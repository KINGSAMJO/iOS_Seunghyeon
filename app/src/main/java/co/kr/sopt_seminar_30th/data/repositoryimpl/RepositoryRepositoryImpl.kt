package co.kr.sopt_seminar_30th.data.repositoryimpl

import co.kr.sopt_seminar_30th.data.datasource.local.RepositoryDao
import co.kr.sopt_seminar_30th.data.mapper.RepositoryMapper
import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation
import co.kr.sopt_seminar_30th.domain.repository.RepositoryRepository
import javax.inject.Inject

class RepositoryRepositoryImpl @Inject constructor(
    private val repositoryDao: RepositoryDao
) : RepositoryRepository {
    override suspend fun insertRepositoryList(repositoryList: List<RepositoryInformation>): List<Long> {
        return repositoryDao.insertRepositoryList(
            repositoryList.map { RepositoryMapper.mapperToRepositoryDto(it) }
        )
    }

    override suspend fun getRepositoryList(): List<RepositoryInformation> {
        return repositoryDao.getRepositoryList().map { RepositoryMapper.mapperToRepositoryInformation(it) }
    }
}