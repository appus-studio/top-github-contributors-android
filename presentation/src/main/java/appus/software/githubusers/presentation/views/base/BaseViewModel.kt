package appus.software.githubusers.presentation.views.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import appus.software.githubusers.presentation.common.SingleLiveEvent
import appus.software.githubusers.presentation.views.base.model.NavigationModel
import appus.software.githubusers.presentation.views.base.model.ProgressModel
import appus.software.githubusers.presentation.views.base.model.ToastModel


abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    val navEvent = MutableLiveData<NavigationModel>()
    val showToast = SingleLiveEvent<ToastModel>()
}
