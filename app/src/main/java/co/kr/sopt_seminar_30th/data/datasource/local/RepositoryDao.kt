package co.kr.sopt_seminar_30th.data.datasource.local

import androidx.room.*
import co.kr.sopt_seminar_30th.data.model.RepositoryDto

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM RepositoryInformation ORDER BY repositoryOrder")
    suspend fun getRepositoryList(): List<RepositoryDto>

    @Insert
    suspend fun insertRepositoryList(repositoryList: List<RepositoryDto>): List<Long>

    @Update
    suspend fun updateRepositoryList(repositoryList: List<RepositoryDto>)

    @Delete
    suspend fun deleteRepository(repository: RepositoryDto)

    @Delete
    suspend fun deleteRepositoryList(repositoryList: List<RepositoryDto>)
}