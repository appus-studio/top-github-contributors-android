package appus.software.githubusers.data.entities

import appus.software.githubusers.domain.model.UserModel
import com.google.gson.annotations.SerializedName

/**
 * Created by bogdan.martynov on 2019-04-24 16:07. top-github-contributors-android
 */

data class AuthorEntity(
    @SerializedName("login")
    val login: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("avatarUrl")
    val avatarUrl: String?,
    @SerializedName("location")
    val location: String?)

fun AuthorEntity.toModel(): UserModel{
    return UserModel(login, id, avatarUrl, location)
}