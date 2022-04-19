package co.kr.sopt_seminar_30th.data.datasource.local

import androidx.room.*
import co.kr.sopt_seminar_30th.data.model.FollowerDto

@Dao
interface FollowerDao {
    @Query("SELECT * FROM FollowerInformation ORDER BY followerOrder")
    suspend fun getFollowerList(): List<FollowerDto>

    @Insert
    suspend fun insertFollowerList(followerList: List<FollowerDto>): List<Long>

    @Update
    suspend fun updateFollowerList(followerList: List<FollowerDto>)

    @Delete
    suspend fun deleteFollower(follower: FollowerDto)

    @Delete
    suspend fun deleteFollowerList(followerList: List<FollowerDto>)
}