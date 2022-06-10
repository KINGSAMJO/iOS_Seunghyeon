package co.kr.sopt_seminar_30th.data.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Authorization")
data class AuthorizationDto(
    @PrimaryKey val userId: String,
    @ColumnInfo(name = "autoLogin") val autoLogin: Boolean
)
