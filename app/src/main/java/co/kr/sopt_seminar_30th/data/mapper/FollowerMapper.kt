package co.kr.sopt_seminar_30th.data.mapper

import co.kr.sopt_seminar_30th.data.model.FollowerDto
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation

object FollowerMapper {
    fun mapperToFollowerInformation(follower: FollowerDto): FollowerInformation {
        return FollowerInformation(
            follower.followerName,
            follower.followerDescription,
            follower.followerImage
        )
    }

    fun mapperToFollowerDto(followerInformation: FollowerInformation): FollowerDto {
        return FollowerDto(
            followerInformation.followerName,
            followerInformation.followerDescription,
            followerInformation.followerImage
        )
    }
}