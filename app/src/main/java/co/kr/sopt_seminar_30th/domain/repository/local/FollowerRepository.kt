package co.kr.sopt_seminar_30th.domain.repository.local

import co.kr.sopt_seminar_30th.domain.entity.tmp.follower.FollowerInformation

interface FollowerRepository {
    suspend fun insertFollowerList(followerList: List<FollowerInformation>): List<Long>
    suspend fun getFollowerList(): List<FollowerInformation>
    suspend fun updateFollowerList(followerList: List<FollowerInformation>)
    suspend fun deleteFollower(follower: FollowerInformation)
    suspend fun deleteFollowerList(followerList: List<FollowerInformation>)
}