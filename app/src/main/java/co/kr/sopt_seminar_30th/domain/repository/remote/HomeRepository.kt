package co.kr.sopt_seminar_30th.domain.repository.remote

import co.kr.sopt_seminar_30th.domain.entity.home.UserFollow
import co.kr.sopt_seminar_30th.domain.entity.home.UserProfile
import co.kr.sopt_seminar_30th.domain.entity.home.UserRepository

interface HomeRepository {
    suspend fun fetchUserInformation(userId: String): Result<UserProfile>
    suspend fun fetchUserFollowers(userId: String): Result<List<UserFollow>>
    suspend fun fetchUserFollowing(userId: String): Result<List<UserFollow>>
    suspend fun fetchUserRepositories(userId: String): Result<List<UserRepository>>
}