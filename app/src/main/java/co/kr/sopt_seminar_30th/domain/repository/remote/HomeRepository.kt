package co.kr.sopt_seminar_30th.domain.repository.remote

import co.kr.sopt_seminar_30th.domain.entity.home.UserFollowInformation
import co.kr.sopt_seminar_30th.domain.entity.home.UserProfileInformation
import co.kr.sopt_seminar_30th.domain.entity.home.UserRepositoryInformation

interface HomeRepository {
    suspend fun fetchUserInformation(userId: String): Result<UserProfileInformation>
    suspend fun fetchUserFollowers(userId: String): Result<List<UserFollowInformation>>
    suspend fun fetchUserFollowing(userId: String): Result<List<UserFollowInformation>>
    suspend fun fetchUserRepositories(userId: String): Result<List<UserRepositoryInformation>>
}