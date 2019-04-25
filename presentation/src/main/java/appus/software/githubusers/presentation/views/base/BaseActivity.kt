package appus.software.githubusers.presentation.views.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import appus.software.githubusers.BR
import appus.software.githubusers.presentation.views.base.model.NavigationModel
import appus.software.githubusers.presentation.views.base.model.ToastModel
import org.koin.androidx.viewmodel.ext.android.viewModelByClass
import kotlin.reflect.KClass


abstract class BaseActivity<D : ViewDataBinding, ViewModelType : BaseViewModel>(clazz: KClass<ViewModelType>) :
        AppCompatActivity(), ViewInterface, BaseView {


    lateinit var viewDataBinding: D
    open val vm: ViewModelType  by viewModelByClass(clazz)
    private var mLoader: View? = null
    protected lateinit var navController: NavController


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    val bindingViewModelId: Int = BR.vm

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewDataBinding.lifecycleOwner = this
        performDataBinding(viewDataBinding)
        lifecycle.addObserver(vm)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        attachViews()
        initViews(savedInstanceState)
        super.onPostCreate(savedInstanceState)
    }

    protected fun initViews(savedInstanceState: Bundle?) {
    }

    protected open fun attachViews() {
    }


    /**
     * @param viewDataBinding ViewDataBinding data of a screen
     */
    protected fun performDataBinding(viewDataBinding: D) {
        viewDataBinding.setVariable(bindingViewModelId, vm)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(vm)
    }



    /**
     * Show toast message
     * @param toastModel Model with settings for toast
     */
    override fun showToast(toastModel: ToastModel) {
        val message: String? = if (toastModel.idResMessage != null) {
            getString(toastModel.idResMessage!!)
        } else {
            toastModel.message
        }
        if (!message.isNullOrEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * Screen navigation
     * @param navModel Model with settings for navController
     */
    override fun navigateTo(navModel: NavigationModel) {
    }

}

interface ViewInterface : LifecycleOwner