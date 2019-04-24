package appus.software.githubusers.data.net

import okhttp3.Interceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


/**
 * Created by bogdan.martynov on 4/12/19 1:15 PM. gitHubUsers
 */


/*
  Technology:
  Retrofit 2 - A type-safe HTTP client
  OkHttp - An HTTP & HTTP/2 client
*/

class GitHubClient {
    private val BASE_URL = "https://api.github.com/"
    private val client: OkHttpClient
    private val retrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            //.addInterceptor(HeaderInterceptor())
            .hostnameVerifier { arg0, arg1 -> true }
            .build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }


    private class HeaderInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val request = original.newBuilder().build()
            return chain.proceed(request)
        }
    }


    fun getGitHubServiceService(): GitHubAPI = retrofit.create(GitHubAPI::class.java)


}
