package appus.software.githubusers.presentation.views.base

import appus.software.githubusers.presentation.views.base.model.NavigationModel
import appus.software.githubusers.presentation.views.base.model.ProgressModel
import appus.software.githubusers.presentation.views.base.model.ToastModel

/**
 * Created by bogdan.martynov on 2019-04-25 10:42. top-github-contributors-android
 */

interface BaseView{
    fun showToast(toastModel: ToastModel)
    fun navigateTo(navModel: NavigationModel)
}