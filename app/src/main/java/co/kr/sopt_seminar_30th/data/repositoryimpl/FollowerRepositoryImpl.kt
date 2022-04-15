package co.kr.sopt_seminar_30th.data.repositoryimpl

import co.kr.sopt_seminar_30th.data.datasource.local.FollowerDao
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.repository.FollowerRepository
import co.kr.sopt_seminar_30th.util.toFollowerDto
import co.kr.sopt_seminar_30th.util.toFollowerInformation
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val followerDao: FollowerDao
): FollowerRepository {
    override suspend fun insertFollowerList(followerList: List<FollowerInformation>): List<Long> {
        return followerDao.insertFollowerList(followerList.map { it.toFollowerDto() })
    }

    override suspend fun getFollowerList(): List<FollowerInformation> {
        return followerDao.getFollowerList().map { it.toFollowerInformation() }
    }
}