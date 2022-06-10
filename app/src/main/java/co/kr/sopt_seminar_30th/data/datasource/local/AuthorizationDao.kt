package co.kr.sopt_seminar_30th.data.datasource.local

import androidx.room.*
import co.kr.sopt_seminar_30th.data.model.dto.AuthorizationDto

@Dao
interface AuthorizationDao {
    @Insert
    suspend fun insertAuthorization(authorizationDto: AuthorizationDto)

    @Delete
    suspend fun deleteAuthorization(authorizationDto: AuthorizationDto)

    @Query("SELECT * FROM Authorization WHERE userId = :id")
    suspend fun getAuthorization(id: String): AuthorizationDto
}