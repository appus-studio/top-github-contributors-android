package appus.software.githubusers.domain.interactors

import appus.software.githubusers.domain.executor.PostExecutionThread
import appus.software.githubusers.domain.executor.ThreadExecutor
import appus.software.githubusers.domain.model.UserModel
import appus.software.githubusers.domain.repository.UserRepository
import io.reactivex.Observable

/**
 * Created by bogdan.martynov on 2019-04-24 18:32. top-github-contributors-android
 */


/**
 * Use case for download specific user from GitHub
 **/
class GetUserUseCase constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val mUserRepository: UserRepository
) : UseCase<UserModel, GetUserUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<UserModel> =
        mUserRepository.getUser(params!!.userName)

    data class Params(val userName: String)
}