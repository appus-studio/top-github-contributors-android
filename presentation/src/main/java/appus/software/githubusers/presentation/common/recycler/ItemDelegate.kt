package appus.software.githubusers.presentation.common.recycler

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.ViewDataBinding
import appus.software.githubusers.BR
import appus.software.githubusers.domain.model.field.Field
import appus.software.githubusers.domain.model.field.FieldType

/**
 * Created by bogdan.martynov on 2019-04-25 11:33. top-github-contributors-android
 */

class ItemDelegate constructor(private val mViewType: FieldType, @LayoutRes private val mLayoutId: Int) : AdapterDelegate<Field>() {
    override val layoutId: Int = mLayoutId
    override fun isForViewType(@NonNull item: Field, position: Int): Boolean = mViewType == item.fieldType

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, @Nullable listener: OnRecyclerItemClick<Field>?): BindingHolder<ViewDataBinding> {
        val holder = BindingHolder.newInstance(inflateViewDataBinding(parent))
        holder.binding.setVariable(BR.actionHandler, listener)
        return holder
    }



    @NonNull
    override fun onBindViewHolder(@NonNull item: Field,
                                  @NonNull holder: BindingHolder<ViewDataBinding>,
                                  position: Int) {
        holder.binding.setVariable(BR.position, position)
        holder.binding.setVariable(BR.model, item)
    }
}