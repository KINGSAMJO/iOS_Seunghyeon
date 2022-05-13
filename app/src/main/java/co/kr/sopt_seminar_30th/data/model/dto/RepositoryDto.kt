package co.kr.sopt_seminar_30th.data.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.kr.sopt_seminar_30th.domain.entity.tmp.repository.RepositoryInformation

@Entity(tableName = "RepositoryInformation")
data class RepositoryDto(
    @PrimaryKey val repositoryName: String,
    @ColumnInfo(name = "repositoryDescription") val repositoryDescription: String,
    @ColumnInfo(name = "repositoryOrder") val repositoryOrder: Int
) {
    fun toRepositoryInformation(): RepositoryInformation = RepositoryInformation(
        repositoryName = this.repositoryName,
        repositoryDescription = this.repositoryDescription,
        repositoryOrder = this.repositoryOrder
    )
}
