package appus.software.githubusers.presentation.views.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import appus.software.githubusers.BR
import appus.software.githubusers.presentation.views.base.model.NavigationModel
import appus.software.githubusers.presentation.views.base.model.ProgressModel
import appus.software.githubusers.presentation.views.base.model.ToastModel
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass


abstract class BaseFragment<D : ViewDataBinding, ViewModelType : BaseViewModel>(clazz: KClass<ViewModelType>) :
    Fragment(), ViewInterface, BaseView {


    protected lateinit var viewDataBinding: D
    open val vm: ViewModelType  by viewModelByClass(clazz)
    protected lateinit var navController: NavController
    protected val bindingViewModelId: Int = BR.vm
    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachFragmentViews(view)
        initViews()
    }


    /**
     * @param viewDataBinding ViewDataBinding data of a screen
     */
    protected open fun performDataBinding(viewDataBinding: D) {
        viewDataBinding.setVariable(bindingViewModelId, vm)
        this.viewDataBinding.executePendingBindings()
    }

    protected open fun initViews(){}


    /**
     * Attach data for a screen
     * @param view View of specific screen
     */
    protected open fun attachFragmentViews(view: View) {
        performDataBinding(viewDataBinding)
        lifecycle.addObserver(vm)
        vm.navEvent.observe(this, Observer { it?.let { navModel -> this.navigateTo(navModel) } })
        vm.showToast.observe(this, Observer { this.showToast(it) })
        navController = NavHostFragment.findNavController(this)
    }

    /**
     * Show toast message
     * @param toastModel Model with settings for toast
     */
    override fun showToast(toastModel: ToastModel) {
        (activity as BaseView).showToast(toastModel)
    }


    /**
     * Screen navigation
     * @param navModel Model with settings for navController
     */
    override fun navigateTo(navModel: NavigationModel) {
        when {
            navModel.popBack -> navController.navigateUp()
            navModel.direction != null -> navModel.direction?.let { navController.navigate(it) }
            else -> navController.navigate(navModel.actionId, navModel.bundle, navModel.navOptions)
        }
        vm.navEvent.value = null
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(vm)
    }
}
