package co.kr.sopt_seminar_30th.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RepositoryInformation")
data class RepositoryDto(
    @PrimaryKey val repositoryName: String,
    @ColumnInfo(name = "repositoryDescription") val repositoryDescription: String
)
