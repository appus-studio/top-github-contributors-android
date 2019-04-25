package appus.software.githubusers.presentation.common

import io.reactivex.observers.DisposableObserver

/**
 * Created by bogdan.martynov on 2019-04-25 11:24. top-github-contributors-android
 */

abstract class ResponseObservable<T>: DisposableObserver<T>() {
    override fun onComplete() {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }
}