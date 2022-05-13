package co.kr.sopt_seminar_30th.domain.entity.home

data class UserFollowInformation(
    val profileImageUrl: String,
    val userId: String,
    var followOrder: Int
)