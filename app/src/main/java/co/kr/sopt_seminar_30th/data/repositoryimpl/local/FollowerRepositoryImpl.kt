package co.kr.sopt_seminar_30th.data.repositoryimpl.local

import co.kr.sopt_seminar_30th.data.datasource.local.FollowerDao
import co.kr.sopt_seminar_30th.domain.entity.tmp.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.repository.local.FollowerRepository
import co.kr.sopt_seminar_30th.util.toFollowerDto
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val followerDao: FollowerDao
) : FollowerRepository {
    override suspend fun insertFollowerList(followerList: List<FollowerInformation>): List<Long> {
        return followerDao.insertFollowerList(followerList.map { it.toFollowerDto() })
    }

    override suspend fun getFollowerList(): List<FollowerInformation> {
        return followerDao.getFollowerList().map { it.toFollowerInformation() }
    }

    override suspend fun updateFollowerList(followerList: List<FollowerInformation>) {
        return followerDao.updateFollowerList(followerList.map { it.toFollowerDto() })
    }

    override suspend fun deleteFollower(follower: FollowerInformation) {
        return followerDao.deleteFollower(follower.toFollowerDto())
    }

    override suspend fun deleteFollowerList(followerList: List<FollowerInformation>) {
        return followerDao.deleteFollowerList(followerList.map { it.toFollowerDto() })
    }
}