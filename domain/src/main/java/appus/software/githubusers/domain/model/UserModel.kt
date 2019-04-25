package appus.software.githubusers.domain.model


/**
 * Created by bogdan.martynov on 4/12/19 2:00 PM. gitHubUsers
 */

data class UserModel(
        val login: String? = "",
        val id: Int? = 0,
        val avatarUrl: String? = "",
        val location: String? = "")
