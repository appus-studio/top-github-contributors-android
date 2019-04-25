package appus.software.githubusers.presentation.common.recycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by bogdan.martynov on 2019-04-25 11:40. top-github-contributors-android
 */

open class BindingHolder<VB : ViewDataBinding> constructor(val binding: VB) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun <VB : ViewDataBinding> newInstance(viewDataBinding: VB): BindingHolder<VB> {
            return BindingHolder(viewDataBinding)
        }
    }
}