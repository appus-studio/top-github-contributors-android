package appus.software.githubusers.data.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    const val BASE_URL = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideHttpClient().build())
            .build()

    private fun provideHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        }
    }


    //APIs
    fun getGitHubApi(): GitHubAPI = retrofit.create(GitHubAPI::class.java)


    //.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithErrorResponseChecker(netErrorChecker))
    /*.apply { responseBodyConverter<Any>(APIError::class.java, arrayOfNulls(0)) }*/
}