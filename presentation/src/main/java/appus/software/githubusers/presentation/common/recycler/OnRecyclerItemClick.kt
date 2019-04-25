package appus.software.githubusers.presentation.common.recycler

/**
 * Created by bogdan.martynov on 2019-04-25 12:16. top-github-contributors-android
 */

interface OnRecyclerItemClick<T> {
    fun onItemClick(model: T, position: Int)
}