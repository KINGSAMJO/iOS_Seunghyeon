package co.kr.sopt_seminar_30th.data.repositoryimpl.local

import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.data.datasource.local.UserDao
import co.kr.sopt_seminar_30th.data.model.dto.UserDto
import co.kr.sopt_seminar_30th.domain.entity.tmp.user.UserInfo
import co.kr.sopt_seminar_30th.domain.repository.local.UserRepository
import co.kr.sopt_seminar_30th.util.toUserDto
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val dataStore: SopthubDataStore
) : UserRepository {
    override suspend fun getUserInformation(userId: String): UserInfo {
        return userDao.getUserInformation(userId).toUserInformation()
    }

//    TODO: 보류
    override suspend fun login(id: String, password: String): Boolean {
//        val userInformation = getUserInformation(id)
//        return if (userInformation.userId == id && userInformation.userPassword == password) {
            dataStore.userId = id
            dataStore.autoLogin = true
            return true
//        } else {
//            false
//        }
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

    override suspend fun updateUserInformation(userInformation: UserInfo): UserInfo {
        userDao.updateUserInformation(userInformation.toUserDto())
        return getUserInformation(userInformation.userId)
    }

    override suspend fun getAutoLogin(): Boolean {
        return dataStore.autoLogin
    }

    override suspend fun getUserId(): String {
        return dataStore.userId
    }

    override suspend fun turnOffAutoLogin() {
        dataStore.autoLogin = false
    }
}