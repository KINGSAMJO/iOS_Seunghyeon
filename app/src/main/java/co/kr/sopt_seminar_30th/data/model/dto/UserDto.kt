package co.kr.sopt_seminar_30th.data.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.kr.sopt_seminar_30th.domain.entity.tmp.user.UserInfo

@Entity(tableName = "UserInformation")
data class UserDto(
    @PrimaryKey val userId: String,
    @ColumnInfo(name = "userPassword") val userPassword: String,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "userAge") val userAge: Int?,
    @ColumnInfo(name = "userMbti") val userMbti: String?,
    @ColumnInfo(name = "userImage") val userImage: String?,
    @ColumnInfo(name = "userDescription") val userDescription: String?
) {
    fun toUserInformation(): UserInfo = UserInfo(
        userId = this.userId,
        userPassword = this.userPassword,
        userName = this.userName,
        userAge = this.userAge,
        userMbti = this.userMbti,
        userImage = this.userImage,
        userDescription = this.userDescription
    )
}