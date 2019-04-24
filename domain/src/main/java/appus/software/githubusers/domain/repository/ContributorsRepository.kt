package appus.software.githubusers.domain.repository

import appus.software.githubusers.domain.model.ContributorModel
import io.reactivex.Observable

/**
 * Created by bogdan.martynov on 2019-04-24 16:15. top-github-contributors-android
 */

interface ContributorsRepository {
    fun getContributors(repoOwner: String, repoName: String): Observable<List<ContributorModel>>
}