package co.kr.sopt_seminar_30th.data.repositoryimpl

import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.data.datasource.local.UserDao
import co.kr.sopt_seminar_30th.data.mapper.UserMapper
import co.kr.sopt_seminar_30th.data.model.UserDto
import co.kr.sopt_seminar_30th.domain.entity.user.UserInformation
import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val dataStore: SopthubDataStore
) : UserRepository {
    override suspend fun getUserInformation(userId: String): UserInformation {
        return UserMapper.mapperToUserInformation(userDao.getUserInformation(userId))
    }

    override suspend fun login(id: String, password: String): Boolean {
        val userInformation = getUserInformation(id)
        return if(userInformation.userId == id && userInformation.userPassword == password) {
            dataStore.userId = id
            true
        } else {
            false
        }
    }

    override suspend fun insertUserInformation(
        id: String,
        password: String,
        name: String
    ): Long {
        return userDao.insertUserInformation(
            UserDto(
                userId = id,
                userPassword = password,
                userName = name,
                0,
                null,
                null,
                null
            )
        )
    }

    override suspend fun updateUserInformation(userInformation: UserInformation): UserInformation {
        userDao.updateUserInformation(UserMapper.mapperToUserDto(userInformation))
        return getUserInformation(userInformation.userId)
    }

    override suspend fun getUserId(): String {
        return dataStore.userId
    }
}