package co.kr.sopt_seminar_30th.data.service.home

import co.kr.sopt_seminar_30th.data.model.response.home.ResponseFetchUserFollowItem
import co.kr.sopt_seminar_30th.data.model.response.home.ResponseFetchUserInformation
import co.kr.sopt_seminar_30th.data.model.response.home.ResponseFetchUserRepositoryItem
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("/users/{userId}")
    suspend fun fetchUserInformation(
        @Path("userId") userId: String
    ): ResponseFetchUserInformation

    @GET("/users/{userId}/followers")
    suspend fun fetchUserFollowers(
        @Path("userId") userId: String
    ): List<ResponseFetchUserFollowItem>

    @GET("/users/{userId}/following")
    suspend fun fetchUserFollowing(
        @Path("userId") userId: String
    ): List<ResponseFetchUserFollowItem>

    @GET("/users/{userId}/repos")
    suspend fun fetchUserRepositories(
        @Path("userId") userId: String
    ): List<ResponseFetchUserRepositoryItem>
}