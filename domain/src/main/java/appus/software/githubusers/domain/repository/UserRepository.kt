package appus.software.githubusers.domain.repository

import appus.software.githubusers.domain.model.UserModel
import io.reactivex.Observable

/**
 * Created by bogdan.martynov on 2019-04-24 16:33. top-github-contributors-android
 */

interface UserRepository {
    fun getUser(userName: String): Observable<UserModel>
}