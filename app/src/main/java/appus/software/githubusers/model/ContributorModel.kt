package appus.software.githubusers.model

import com.google.gson.annotations.SerializedName

/**
 * Created by bogdan.martynov on 4/12/19 2:01 PM. gitHubUsers
 */

data class ContributorModel(
    @SerializedName("total")
    val total: Int,
    @SerializedName("author")
    val author: AuthorModel
)