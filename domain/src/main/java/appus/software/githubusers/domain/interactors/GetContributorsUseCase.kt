package appus.software.githubusers.domain.interactors

import appus.software.githubusers.domain.executor.PostExecutionThread
import appus.software.githubusers.domain.executor.ThreadExecutor
import appus.software.githubusers.domain.model.ContributorModel
import appus.software.githubusers.domain.repository.ContributorsRepository
import io.reactivex.Observable

/**
 * Created by bogdan.martynov on 2019-04-24 18:27. top-github-contributors-android
 */

class GetContributorsUseCase constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val mContributorsRepository: ContributorsRepository
) : UseCase<List<ContributorModel>, GetContributorsUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<List<ContributorModel>> =
        mContributorsRepository.getContributors(params!!.repoOwner, params.repoName)

    data class Params(val repoOwner: String, val repoName: String)
}