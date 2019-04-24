package appus.software.githubusers.data.net.errors

import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken



data class APIError(@SerializedName("message") var message: String? = "",
                    @SerializedName("errors") var errors: Map<String, *>? = null) {
    companion object {
        val LIST_TOKEN = object : TypeToken<APIError>() {
        }.type
    }
}