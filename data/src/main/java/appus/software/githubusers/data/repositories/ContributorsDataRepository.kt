package appus.software.githubusers.data.repositories

import appus.software.githubusers.data.entities.toModel
import appus.software.githubusers.data.net.GitHubAPI
import appus.software.githubusers.domain.model.ContributorModel
import appus.software.githubusers.domain.repository.ContributorsRepository
import io.reactivex.Observable

/**
 * Created by bogdan.martynov on 2019-04-24 16:14. top-github-contributors-android
 */

class ContributorsDataRepository(private val mGitHubAPI: GitHubAPI): ContributorsRepository {
    override fun getContributors(repoOwner: String, repoName: String): Observable<List<ContributorModel>> {
        return mGitHubAPI.getContributors(repoOwner, repoName)
            .map {  it.map { it.toModel() } }
    }
}