package co.kr.sopt_seminar_30th.data.model.response.home

import co.kr.sopt_seminar_30th.domain.entity.home.UserFollow
import com.google.gson.annotations.SerializedName

data class UserFollowResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("gists_url")
    val gistsUrl: String,
    @SerializedName("gravatar_id")
    val gravataId: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    val login: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    @SerializedName("starred_url")
    val starredUrl: String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    val type: String,
    val url: String
) {
    fun toUserFollowInformation(): UserFollow {
        return UserFollow(
            profileImageUrl = avatarUrl,
            userId = login,
            followOrder = 0
        )
    }
}