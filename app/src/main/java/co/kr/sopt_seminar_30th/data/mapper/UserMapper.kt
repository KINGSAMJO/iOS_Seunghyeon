package co.kr.sopt_seminar_30th.data.mapper

import co.kr.sopt_seminar_30th.data.model.UserDto
import co.kr.sopt_seminar_30th.domain.entity.user.SignUpUserInformation
import co.kr.sopt_seminar_30th.domain.entity.user.UserInformation

object UserMapper {
    fun mapperToUserInformation(user: UserDto): UserInformation {
        return UserInformation(
            userId = user.userId,
            userPassword = user.userPassword,
            userName = user.userName,
            userAge = user.userAge,
            userMbti = user.userMbti,
            userImage = user.userImage,
            userDescription = user.userDescription
        )
    }

    fun mapperToUserDto(userInformation: UserInformation): UserDto {
        return UserDto(
            userId = userInformation.userId,
            userPassword = userInformation.userPassword,
            userName = userInformation.userName,
            userAge = userInformation.userAge,
            userMbti = userInformation.userMbti,
            userImage = userInformation.userImage,
            userDescription = userInformation.userDescription
        )
    }

    fun mapperToUserDto(signUpUserInformation: SignUpUserInformation): UserDto {
        return UserDto(
            userId = signUpUserInformation.userId,
            userPassword = signUpUserInformation.userPassword,
            userName = signUpUserInformation.userName,
            0,
            null,
            null,
            null
        )
    }
}