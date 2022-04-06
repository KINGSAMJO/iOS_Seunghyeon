package co.kr.sopt_seminar_30th.domain.usecase.base

import java.lang.Exception

sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
}
