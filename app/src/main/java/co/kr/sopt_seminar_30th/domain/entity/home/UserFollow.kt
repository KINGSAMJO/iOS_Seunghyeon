package co.kr.sopt_seminar_30th.domain.entity.home

data class UserFollow(
    val profileImageUrl: String,
    val userId: String,
    var followOrder: Int
)