package co.kr.sopt_seminar_30th.data.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.kr.sopt_seminar_30th.domain.entity.tmp.follower.FollowerInformation

@Entity(tableName = "FollowerInformation")
data class FollowerDto(
    @PrimaryKey val followerName: String,
    @ColumnInfo(name = "followerDescription") val followerDescription: String,
    @ColumnInfo(name = "followerImage") val followerImage: String?,
    @ColumnInfo(name = "followerOrder") val followerOrder: Int
) {
    fun toFollowerInformation(): FollowerInformation = FollowerInformation(
        followerName = this.followerName,
        followerDescription = this.followerDescription,
        followerImage = this.followerImage,
        followerOrder = this.followerOrder
    )
}
