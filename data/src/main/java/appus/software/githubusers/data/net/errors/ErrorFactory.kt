package appus.software.githubusers.data.net.errors

import com.google.gson.Gson
import retrofit2.Response


class ErrorFactory : ErrorProvider {

    override fun create(type: ErrorType, response: Response<*>): AppException {
        val apiError = parseApiError(response)
        return when (type) {
            ErrorType.TIMEOUT -> return AppException(
                if (response.errorBody() != null) response.errorBody()?.toString() else "",
                type)
            ErrorType.WRONG_DATA, ErrorType.BAD_REQUEST, ErrorType.INTERNET ->
                AppException(apiError?.first, type, apiError?.second)
            ErrorType.UNAUTHORIZED -> return AppException(apiError?.first, type, apiError?.second)
            else -> return AppException(
                if (response.errorBody() != null) response.errorBody()!!.toString() else "",
                ErrorType.DEFAULT,
                apiError?.second)
        }
    }

    override fun create(type: ErrorType, message: String): AppException {
        return AppException(message, type)
    }

    private fun parseApiError(response: Response<*>): Pair<String, MutableMap<String, String>?>? {
        fun parseError(errors: Map<String, *>?): MutableMap<String, String> {
            return mutableMapOf<String, String>().apply {
                errors?.forEach { it ->
                    put(it.key, (it.value as? List<*>)?.let { it[0] as String }
                        ?: it.value as String)
                }
            }
        }

        return try {
            response.errorBody()
                ?.let { it -> Gson().fromJson<APIError>(it.string(), APIError.LIST_TOKEN) }
                ?.let { error -> (error.message ?: "") to parseError(error.errors) }
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private var INSTANCE: ErrorFactory? = null
        val instance: ErrorFactory
            get() {
                if (INSTANCE == null) {
                    INSTANCE = ErrorFactory()
                }
                return INSTANCE as ErrorFactory
            }
    }
}
