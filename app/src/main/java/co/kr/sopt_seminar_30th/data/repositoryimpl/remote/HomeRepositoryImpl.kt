package co.kr.sopt_seminar_30th.data.repositoryimpl.remote

import co.kr.sopt_seminar_30th.data.service.home.HomeService
import co.kr.sopt_seminar_30th.domain.entity.home.UserFollow
import co.kr.sopt_seminar_30th.domain.entity.home.UserProfile
import co.kr.sopt_seminar_30th.domain.entity.home.UserRepository
import co.kr.sopt_seminar_30th.domain.repository.remote.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val service: HomeService,
    private val coroutineDispatcher: CoroutineDispatcher
) : HomeRepository {
    override suspend fun fetchUserInformation(userId: String): Result<UserProfile> =
        withContext(coroutineDispatcher) {
            kotlin.runCatching {
                service.fetchUserInformation(userId).toHomeUserInformation()
            }
        }

    override suspend fun fetchUserFollowers(userId: String): Result<List<UserFollow>> =
        withContext(coroutineDispatcher) {
            kotlin.runCatching {
                service.fetchUserFollowers(userId, 100)
                    .mapIndexed { index, responseFetchUserFollowerItem ->
                        responseFetchUserFollowerItem.toUserFollowInformation().apply {
                            followOrder = index
                        }
                    }
            }
        }

    override suspend fun fetchUserFollowing(userId: String): Result<List<UserFollow>> =
        withContext(coroutineDispatcher) {
            kotlin.runCatching {
                service.fetchUserFollowing(userId, 100)
                    .mapIndexed { index, responseFetchUserFollowItem ->
                        responseFetchUserFollowItem.toUserFollowInformation().apply {
                            followOrder = index
                        }
                    }
            }
        }

    override suspend fun fetchUserRepositories(userId: String): Result<List<UserRepository>> =
        withContext(coroutineDispatcher) {
            kotlin.runCatching {
                service.fetchUserRepositories(userId)
                    .mapIndexed { index, responseFetchUserRepositoryItem ->
                        responseFetchUserRepositoryItem.toUserRepositoryInformation().apply {
                            repositoryOrder = index
                        }
                    }
            }
        }
}