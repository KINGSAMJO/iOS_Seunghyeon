package co.kr.sopt_seminar_30th.domain.entity.user

data class UserInformation(
    val userId: String,
    val userPassword: String,
    val userName: String,
    val userAge: Int?,
    val userMbti: String?,
    val userImage: String?,
    val userDescription: String?
)
