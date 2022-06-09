package co.kr.sopt_seminar_30th.domain.entity.tmp.user

data class UserInfo(
    val userId: String,
    val userPassword: String,
    val userName: String,
    val userAge: Int?,
    val userMbti: String?,
    val userImage: String?,
    val userDescription: String?
)
