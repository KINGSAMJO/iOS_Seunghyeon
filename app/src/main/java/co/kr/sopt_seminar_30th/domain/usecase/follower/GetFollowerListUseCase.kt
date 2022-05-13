package co.kr.sopt_seminar_30th.domain.usecase.follower

import co.kr.sopt_seminar_30th.di.IoDispatcher
import co.kr.sopt_seminar_30th.domain.entity.tmp.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.repository.local.FollowerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFollowerListUseCase @Inject constructor(
    private val repository: FollowerRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): List<FollowerInformation> {
        return withContext(coroutineDispatcher) {
            repository.getFollowerList()
        }
    }
}