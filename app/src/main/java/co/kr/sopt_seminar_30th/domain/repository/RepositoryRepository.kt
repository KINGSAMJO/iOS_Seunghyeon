package co.kr.sopt_seminar_30th.domain.repository

import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation

interface RepositoryRepository {
    suspend fun insertRepositoryList(repositoryList: List<RepositoryInformation>): List<Long>
    suspend fun getRepositoryList(): List<RepositoryInformation>
}