package appus.software.githubusers.data.repositories

import appus.software.githubusers.data.entities.toModel
import appus.software.githubusers.data.net.GitHubAPI
import appus.software.githubusers.domain.model.UserModel
import appus.software.githubusers.domain.repository.UserRepository
import io.reactivex.Observable

/**
 * Created by bogdan.martynov on 2019-04-24 16:39. top-github-contributors-android
 */

class UserDataRepository (private val mGitHubAPI: GitHubAPI):UserRepository {
    override fun getUser(userName: String): Observable<UserModel> {
        return mGitHubAPI.getUserData(userName).map { it.toModel() }
    }
}