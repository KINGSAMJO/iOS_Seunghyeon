package co.kr.sopt_seminar_30th.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.kr.sopt_seminar_30th.data.model.FollowerDto

@Dao
interface FollowerDao {
    @Query("SELECT * FROM FollowerInformation")
    suspend fun getFollowerList(): List<FollowerDto>

    @Insert
    suspend fun insertFollowerList(followerList: List<FollowerDto>): List<Long>
}