package co.kr.sopt_seminar_30th.domain.usecase.follower

import co.kr.sopt_seminar_30th.di.IoDispatcher
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.repository.FollowerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteFollowerListUseCase @Inject constructor(
    private val repository: FollowerRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(followerList: List<FollowerInformation>) {
        withContext(coroutineDispatcher) {
            repository.deleteFollowerList(followerList)
        }
    }
}