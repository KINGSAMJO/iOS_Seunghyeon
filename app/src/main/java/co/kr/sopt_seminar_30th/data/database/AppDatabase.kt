package co.kr.sopt_seminar_30th.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.kr.sopt_seminar_30th.data.datasource.local.FollowerDao
import co.kr.sopt_seminar_30th.data.datasource.local.RepositoryDao
import co.kr.sopt_seminar_30th.data.datasource.local.UserDao
import co.kr.sopt_seminar_30th.data.model.dto.FollowerDto
import co.kr.sopt_seminar_30th.data.model.dto.RepositoryDto
import co.kr.sopt_seminar_30th.data.model.dto.UserDto

@Database(entities = [UserDto::class, FollowerDto::class, RepositoryDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun followerDao(): FollowerDao
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        fun getInstance(context: Context): AppDatabase = Room
            .databaseBuilder(context, AppDatabase::class.java, "seminar.db")
            .build()
    }
}