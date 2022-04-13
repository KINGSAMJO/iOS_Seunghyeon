package co.kr.sopt_seminar_30th.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.kr.sopt_seminar_30th.data.model.RepositoryDto

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM RepositoryInformation")
    suspend fun getRepositoryList(): List<RepositoryDto>

    @Insert
    suspend fun insertRepositoryList(repositoryList: List<RepositoryDto>): List<Long>
}