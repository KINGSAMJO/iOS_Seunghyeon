package co.kr.sopt_seminar_30th.domain.entity.home

data class UserRepository(
    val name: String,
    val language: String,
    var repositoryOrder: Int
)
