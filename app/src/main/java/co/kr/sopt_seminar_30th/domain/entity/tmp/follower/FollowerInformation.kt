package co.kr.sopt_seminar_30th.domain.entity.tmp.follower

data class FollowerInformation(
    val followerName: String,
    val followerDescription: String,
    val followerImage: String?,
    var followerOrder: Int
)
