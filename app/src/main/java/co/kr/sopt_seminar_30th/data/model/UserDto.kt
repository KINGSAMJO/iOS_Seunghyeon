package co.kr.sopt_seminar_30th.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserInformation")
data class UserDto(
    @PrimaryKey val userId: String,
    @ColumnInfo(name="userPassword") val userPassword: String,
    @ColumnInfo(name="userName") val userName: String,
    @ColumnInfo(name="userAge") val userAge: Int?,
    @ColumnInfo(name="userMbti") val userMbti: String?,
    @ColumnInfo(name="userImage") val userImage: String?,
    @ColumnInfo(name="userDescription") val userDescription: String?
)