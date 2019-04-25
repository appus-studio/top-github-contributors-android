package appus.software.githubusers.domain.interactors

import appus.software.githubusers.domain.executor.PostExecutionThread
import appus.software.githubusers.domain.executor.ThreadExecutor
import appus.software.githubusers.domain.model.ContributorModel
import appus.software.githubusers.domain.repository.ContributorsRepository
import io.reactivex.Observable

/**
 * Created by bogdan.martynov on 2019-04-24 18:27. top-github-contributors-android
 */


/**
 * Use case for download list of contributors from GitHub and sort of downloaded data
 **/
class GetContributorsUseCase constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val mContributorsRepository: ContributorsRepository
) : UseCase<List<ContributorModel>, GetContributorsUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<List<ContributorModel>> =
        mContributorsRepository.getContributors(params!!.repoOwner, params.repoName)
                .map(this::sortList)


    /**
     * Sort of contributors list: get first 25 contributors sort by count of commits
     * @param list not sorted list from GitHub
     * @return sorted list
     */
    private fun sortList(list:List<ContributorModel>):List<ContributorModel>{
        val limit = when{
            list.isEmpty() -> 0
            list.size >= LIMIT -> LIMIT
            else -> list.size
        }.apply { this - 1 }
        return list.reversed().subList(0, limit).sortedBy { it.total }.reversed()
    }

    data class Params(val repoOwner: String, val repoName: String)

    companion object{
        private const val LIMIT = 25
    }
}