package appus.software.githubusers.data.net.errors

import java.lang.RuntimeException

/**
 * Created by bogdan.martynov on 2019-04-24 16:48. top-github-contributors-android
 */

data class AppException(
    override var message: String? = null,
    var errorType: ErrorType? = null,
    var errorMap: Map<String, String>? = null): RuntimeException()