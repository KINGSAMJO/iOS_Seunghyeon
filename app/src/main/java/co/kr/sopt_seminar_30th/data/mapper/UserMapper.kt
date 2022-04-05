package co.kr.sopt_seminar_30th.data.mapper

import co.kr.sopt_seminar_30th.data.model.User
import co.kr.sopt_seminar_30th.domain.entity.UserInformation

object UserMapper {
    fun mapperToUserInformation(user: User): UserInformation {
        return UserInformation(
            userId = user.userId,
            userPassword = user.userPassword,
            userName = user.userName,
            userAge = user.userAge,
            userMbti = user.userMbti,
            userImage = user.userImage
        )
    }

    fun mapperToUser(userInformation: UserInformation): User {
        return User(
            userId = userInformation.userId,
            userPassword = userInformation.userPassword,
            userName = userInformation.userName,
            userAge = userInformation.userAge,
            userMbti = userInformation.userMbti,
            userImage = userInformation.userImage
        )
    }
}