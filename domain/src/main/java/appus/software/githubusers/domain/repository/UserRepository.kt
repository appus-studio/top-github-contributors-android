package appus.software.githubusers.domain.repository

import appus.software.githubusers.domain.model.UserModel
import io.reactivex.Observable

/**
 * Created by bogdan.martynov on 2019-04-24 16:33. top-github-contributors-android
 */

interface UserRepository {
    /**
     * Download specific data about user on GitHub by the user's login
     * @param userName login of a user on GitHub
     */
    fun getUser(userName: String): Observable<UserModel>
}