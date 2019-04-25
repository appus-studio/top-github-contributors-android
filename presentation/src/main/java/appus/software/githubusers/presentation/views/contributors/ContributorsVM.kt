package appus.software.githubusers.presentation.views.contributors

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import appus.software.githubusers.R
import appus.software.githubusers.domain.interactors.GetContributorsUseCase
import appus.software.githubusers.domain.interactors.GetUserUseCase
import appus.software.githubusers.domain.model.ContributorModel
import appus.software.githubusers.domain.model.UserModel
import appus.software.githubusers.presentation.common.ResponseObservable
import appus.software.githubusers.presentation.common.SingleLiveEvent
import appus.software.githubusers.presentation.common.recycler.ItemDelegate
import appus.software.githubusers.presentation.common.recycler.ListConfig
import appus.software.githubusers.presentation.common.recycler.ListDelegateAdapter
import appus.software.githubusers.presentation.common.recycler.OnRecyclerItemClick
import appus.software.githubusers.domain.model.field.Field
import appus.software.githubusers.domain.model.field.FieldType
import appus.software.githubusers.presentation.views.base.BaseViewModel
import appus.software.githubusers.presentation.views.base.model.ProgressModel
import appus.software.githubusers.presentation.views.base.model.ToastModel

/**
 * Created by bogdan.martynov on 2019-04-24 16:00. top-github-contributors-android
 */

class ContributorsVM (
    private val mGetUserUseCase: GetUserUseCase,
    private val mGetContributorsUseCase: GetContributorsUseCase): BaseViewModel(){


    val listConfig: ListConfig.Builder<Field>
    val showPullToRefresh = SingleLiveEvent<Boolean>()
    internal val showUserLocation = SingleLiveEvent<String>()

    init {
        val itemsAdapter = ListDelegateAdapter.Builder<Field>()
            .addDelegate(ItemDelegate(FieldType.CONTRIBUTOR, R.layout.item_contributor))
            .setListener(object : OnRecyclerItemClick<Field>{ override fun onItemClick(model: Field, position: Int) { onItemClicked(model) } })
            .build()

        listConfig = ListConfig.Builder<Field>(itemsAdapter)
            .setLayoutManagerProvider(ListConfig.SimpleLinearLayoutManagerProvider())
            .setDefaultDividerEnabled(true)
    }

    fun refresh(){
        showPullToRefresh.value = false
        loadContributors(true)
    }

    private fun loadContributors(refresh: Boolean) {
        if (!refresh) {
            if (listConfig.adapter.itemCount > 0 || showPullToRefresh.value == true) return
        }

        mGetContributorsUseCase.execute(object: ResponseObservable<List<ContributorModel>>(){
            override fun onStart() {
                showPullToRefresh.value = true
            }
            override fun onNext(t: List<ContributorModel>) {
                showContributors(t)
            }

            override fun onComplete() {
                showPullToRefresh.value = false
            }

            override fun onError(e: Throwable) {
                showPullToRefresh.value = false
                showToast.value = ToastModel(idResMessage = R.string.loading_error)
            }
        }, GetContributorsUseCase.Params(REPO_OWNER, REPO_NAME))
    }

    private fun showContributors(contributors: List<ContributorModel>){
        listConfig.listDelegateAdapter.updateItems(contributors)
    }

    private fun loadUserData(userName: String){
        mGetUserUseCase.execute(object: ResponseObservable<UserModel>(){
            override fun onNext(t: UserModel) {
                if (t.location.isNullOrEmpty()){
                    showToast.value = ToastModel(idResMessage = R.string.error_user_empty_location)
                } else{
                     t.location?.let{
                         showUserLocation.value = it.replace(' ', '+').replace(",", "")
                    }
                }
            }
            override fun onError(e: Throwable) {
                showToast.value = ToastModel(idResMessage = R.string.loading_error)
            }
        }, GetUserUseCase.Params(userName))
    }

    private fun onItemClicked(field: Field){
        when(field.fieldType){
            FieldType.CONTRIBUTOR -> {
                field as ContributorModel
                loadUserData(field.user.login ?: return)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart(){
        loadContributors(false)
    }

    companion object{
        private const val REPO_OWNER = "JetBrains"
        private const val REPO_NAME = "kotlin"
    }
}