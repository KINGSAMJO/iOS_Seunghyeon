package co.kr.sopt_seminar_30th.data.repositoryimpl

import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.data.datasource.local.UserDao
import co.kr.sopt_seminar_30th.data.mapper.UserMapper
import co.kr.sopt_seminar_30th.domain.entity.user.LoginUserInformation
import co.kr.sopt_seminar_30th.domain.entity.user.SignUpUserInformation
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

    override suspend fun login(loginUserInformation: LoginUserInformation): Boolean {
        val userInformation = getUserInformation(loginUserInformation.userId)
        return if(userInformation.userId == loginUserInformation.userId && userInformation.userPassword == loginUserInformation.userPassword) {
            dataStore.userId = loginUserInformation.userId
            true
        } else {
            false
        }
    }

    override suspend fun insertUserInformation(signUpUserInformation: SignUpUserInformation): Long {
        return userDao.insertUserInformation(UserMapper.mapperToUserDto(signUpUserInformation))
    }

    override suspend fun updateUserInformation(userInformation: UserInformation): UserInformation {
        userDao.updateUserInformation(UserMapper.mapperToUserDto(userInformation))
        return getUserInformation(userInformation.userId)
    }

    override suspend fun getUserId(): String {
        return dataStore.userId
    }
}