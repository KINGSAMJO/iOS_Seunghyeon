package co.kr.sopt_seminar_30th.domain.repository

import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation

interface RepositoryRepository {
    suspend fun insertRepositoryList(repositoryList: List<RepositoryInformation>): List<Long>
    suspend fun getRepositoryList(): List<RepositoryInformation>
    suspend fun updateRepositoryList(repositoryList: List<RepositoryInformation>)
    suspend fun deleteRepository(repository: RepositoryInformation)
    suspend fun deleteRepositoryList(repositoryList: List<RepositoryInformation>)
}