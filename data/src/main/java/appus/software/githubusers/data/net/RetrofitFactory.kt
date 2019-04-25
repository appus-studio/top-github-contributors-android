package appus.software.githubusers.data.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "https://api.github.com/"

    /**
     * Retrofit initialization
     */
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideHttpClient().build())
            .build()


    /**
     * Provide OkHttpClient
     */
    private fun provideHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        }
    }


    /**
     * Provide GitHubAPI
     */
    fun getGitHubApi(): GitHubAPI = retrofit.create(GitHubAPI::class.java)

}