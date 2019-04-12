package appus.software.githubusers.net

import appus.software.githubusers.model.AuthorModel
import appus.software.githubusers.model.ContributorModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by bogdan.martynov on 4/12/19 1:20 PM. gitHubUsers
 */

interface GitHubService {

    @GET("users/{user_name}")
    fun getUserData(@Path("user_name") userName: String): Call<AuthorModel>

    @GET("repos/{repo_owner}/{repo_name}/stats/contributors")
    fun getContributors(
        @Path("repo_owner") repoOwner: String,
        @Path("repo_name") repoName: String): Call<List<ContributorModel>>

}