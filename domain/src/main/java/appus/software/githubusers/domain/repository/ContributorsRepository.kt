package appus.software.githubusers.domain.repository

import appus.software.githubusers.domain.model.ContributorModel
import io.reactivex.Observable

/**
 * Created by bogdan.martynov on 2019-04-24 16:15. top-github-contributors-android
 */

interface ContributorsRepository {
    /**
     * Download contributors from specific repository from GitHub
     * @param repoOwner Owner name of a repository on GitHub
     * @param repoName Name of repository
     */
    fun getContributors(repoOwner: String, repoName: String): Observable<List<ContributorModel>>
}