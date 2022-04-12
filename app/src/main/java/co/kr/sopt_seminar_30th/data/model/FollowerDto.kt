package co.kr.sopt_seminar_30th.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FollowerInformation")
data class FollowerDto(
    @PrimaryKey val followerName: String,
    @ColumnInfo(name = "followerDescription") val followerDescription: String,
    @ColumnInfo(name = "followerImage") val followerImage: String?
)
