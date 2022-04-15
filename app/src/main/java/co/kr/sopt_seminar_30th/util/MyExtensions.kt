package co.kr.sopt_seminar_30th.util

import android.content.res.Resources
import co.kr.sopt_seminar_30th.data.model.FollowerDto
import co.kr.sopt_seminar_30th.data.model.RepositoryDto
import co.kr.sopt_seminar_30th.data.model.UserDto
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation
import co.kr.sopt_seminar_30th.domain.entity.user.UserInformation

fun Int.dpToPx(): Int = this * Resources.getSystem().displayMetrics.density.toInt()
fun Int.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun UserInformation.toUserDto(): UserDto = UserDto(
    userId = this.userId,
    userPassword = this.userPassword,
    userName = this.userName,
    userAge = this.userAge,
    userMbti = this.userMbti,
    userImage = this.userImage,
    userDescription = this.userDescription
)

fun UserDto.toUserInformation(): UserInformation = UserInformation(
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
    repositoryDescription = this.repositoryDescription
)

fun RepositoryDto.toRepositoryInformation(): RepositoryInformation = RepositoryInformation(
    repositoryName = this.repositoryName,
    repositoryDescription = this.repositoryDescription
)

fun FollowerInformation.toFollowerDto(): FollowerDto = FollowerDto(
    followerName = this.followerName,
    followerDescription = this.followerDescription,
    followerImage = this.followerImage
)

fun FollowerDto.toFollowerInformation(): FollowerInformation = FollowerInformation(
    followerName = this.followerName,
    followerDescription = this.followerDescription,
    followerImage = this.followerImage
)