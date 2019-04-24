package appus.software.githubusers.data.net.errors

import retrofit2.Response

/**
 * Created by bogdan.martynov on 2019-04-24 16:54. top-github-contributors-android
 */

interface ErrorProvider {
    fun create(type: ErrorType, response: Response<*>): AppException
    fun create(type: ErrorType, message: String): AppException
}