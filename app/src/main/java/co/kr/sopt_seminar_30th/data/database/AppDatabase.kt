package co.kr.sopt_seminar_30th.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.kr.sopt_seminar_30th.data.datasource.local.FollowerDao
import co.kr.sopt_seminar_30th.data.datasource.local.UserDao
import co.kr.sopt_seminar_30th.data.model.FollowerDto
import co.kr.sopt_seminar_30th.data.model.UserDto

@Database(entities = [UserDto::class, FollowerDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun followerDao(): FollowerDao

    companion object {
        fun getInstance(context: Context): AppDatabase = Room
            .databaseBuilder(context, AppDatabase::class.java, "seminar.db")
            .build()
    }
}