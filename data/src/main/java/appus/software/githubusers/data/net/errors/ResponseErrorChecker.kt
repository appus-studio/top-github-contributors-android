package appus.software.githubusers.data.net.errors

import retrofit2.Response

class ResponseErrorChecker(private val mErrorProvider: ErrorProvider = ErrorFactory()) : NetErrorChecker {

    override fun <T> checkResponse(response: Response<T>): AppException {
        return when (response.code()) {
            400 -> mErrorProvider.create(ErrorType.INTERNET, response)
            401 -> mErrorProvider.create(ErrorType.UNAUTHORIZED, response)
            408 -> mErrorProvider.create(ErrorType.TIMEOUT, response)
            422 -> mErrorProvider.create(ErrorType.WRONG_DATA, response)
            403 -> mErrorProvider.create(ErrorType.WRONG_DATA, response)
            else -> mErrorProvider.create(ErrorType.DEFAULT, response)
        }
    }
}

interface NetErrorChecker {
    fun <T> checkResponse(response: Response<T>): AppException
}
