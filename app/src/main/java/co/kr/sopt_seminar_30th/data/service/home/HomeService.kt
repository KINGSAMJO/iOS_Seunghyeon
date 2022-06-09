package co.kr.sopt_seminar_30th.data.service.home

import co.kr.sopt_seminar_30th.data.model.response.home.UserFollowResponse
import co.kr.sopt_seminar_30th.data.model.response.home.UserInformationResponse
import co.kr.sopt_seminar_30th.data.model.response.home.UserRepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {
    @GET("/users/{userId}")
    suspend fun fetchUserInformation(
        @Path("userId") userId: String
    ): UserInformationResponse

    @GET("/users/{userId}/followers")
    suspend fun fetchUserFollowers(
        @Path("userId") userId: String,
        @Query("per_page") perPage: Int
    ): List<UserFollowResponse>

    @GET("/users/{userId}/following")
    suspend fun fetchUserFollowing(
        @Path("userId") userId: String,
        @Query("per_page") perPage: Int
    ): List<UserFollowResponse>

    @GET("/users/{userId}/repos")
    suspend fun fetchUserRepositories(
        @Path("userId") userId: String
    ): List<UserRepositoryResponse>
}