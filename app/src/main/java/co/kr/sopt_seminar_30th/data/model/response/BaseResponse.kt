package co.kr.sopt_seminar_30th.data.model.response

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T
)