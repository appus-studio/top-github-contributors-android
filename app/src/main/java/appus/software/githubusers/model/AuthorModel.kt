package appus.software.githubusers.model

import com.google.gson.annotations.SerializedName

/**
 * Created by bogdan.martynov on 4/12/19 2:00 PM. gitHubUsers
 */

data class AuthorModel(
    @SerializedName("login")
    val login: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("avatar_url")
    val avatar_url: String?,
    @SerializedName("location")
    val location: String?)
