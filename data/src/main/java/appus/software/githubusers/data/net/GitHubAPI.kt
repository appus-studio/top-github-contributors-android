package appus.software.githubusers.data.net

import appus.software.githubusers.data.entities.AuthorEntity
import appus.software.githubusers.data.entities.ContributorEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by bogdan.martynov on 4/12/19 1:20 PM. gitHubUsers
 */

interface GitHubAPI {

    @GET("users/{user_name}")
    fun getUserData(@Path("user_name") userName: String): Observable<AuthorEntity>

    @GET("repos/{repo_owner}/{repo_name}/stats/contributors")
    fun getContributors(
        @Path("repo_owner") repoOwner: String,
        @Path("repo_name") repoName: String): Observable<List<ContributorEntity>>

}