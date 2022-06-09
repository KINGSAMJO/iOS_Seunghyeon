package co.kr.sopt_seminar_30th.util

import android.content.res.Resources
import co.kr.sopt_seminar_30th.data.model.dto.FollowerDto
import co.kr.sopt_seminar_30th.data.model.dto.RepositoryDto
import co.kr.sopt_seminar_30th.data.model.dto.UserDto
import co.kr.sopt_seminar_30th.domain.entity.tmp.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.entity.tmp.repository.RepositoryInformation
import co.kr.sopt_seminar_30th.domain.entity.tmp.user.UserInfo

fun Int.dpToPx(): Int = this * Resources.getSystem().displayMetrics.density.toInt()
fun Int.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun UserInfo.toUserDto(): UserDto = UserDto(
    userId = this.userId,
    userPassword = this.userPassword,
    userName = this.userName,
    userAge = this.userAge,
    userMbti = this.userMbti,
    userImage = this.userImage,
    userDescription = this.userDescription
)

fun RepositoryInformation.toRepositoryDto(): RepositoryDto = RepositoryDto(
    repositoryName = this.repositoryName,
    repositoryDescription = this.repositoryDescription,
    repositoryOrder = this.repositoryOrder
)

fun FollowerInformation.toFollowerDto(): FollowerDto = FollowerDto(
    followerName = this.followerName,
    followerDescription = this.followerDescription,
    followerImage = this.followerImage,
    followerOrder = this.followerOrder
)