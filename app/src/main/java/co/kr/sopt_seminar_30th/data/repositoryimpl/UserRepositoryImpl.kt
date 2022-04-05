package co.kr.sopt_seminar_30th.data.repositoryimpl

import co.kr.sopt_seminar_30th.data.datasource.local.UserDao
import co.kr.sopt_seminar_30th.data.mapper.UserMapper
import co.kr.sopt_seminar_30th.domain.entity.UserInformation
import co.kr.sopt_seminar_30th.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getUserInformation(userId: String): UserInformation {
        return UserMapper.mapperToUserInformation(userDao.getUserInformation(userId))
    }

    override suspend fun insertUserInformation(userInformation: UserInformation): Long {
        return userDao.insertUserInformation(UserMapper.mapperToUser(userInformation))
    }

    override suspend fun updateUserInformation(userInformation: UserInformation) {
        userDao.updateUserInformation(UserMapper.mapperToUser(userInformation))
    }
}