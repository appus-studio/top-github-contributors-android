package appus.software.githubusers.data.entities

import appus.software.githubusers.domain.model.ContributorModel
import appus.software.githubusers.domain.model.UserModel
import com.google.gson.annotations.SerializedName

/**
 * Created by bogdan.martynov on 2019-04-24 16:12. top-github-contributors-android
 */

data class ContributorEntity(
    @SerializedName("total")
    val total: Int?,
    @SerializedName("author")
    val author: AuthorEntity?
)

fun ContributorEntity.toModel(): ContributorModel{
    return ContributorModel(total?:0, author?.toModel() ?: UserModel())
}

