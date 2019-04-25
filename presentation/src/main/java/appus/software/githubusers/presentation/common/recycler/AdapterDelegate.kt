package appus.software.githubusers.presentation.common.recycler


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * This delegate provide method to hook in this delegate to [RecyclerView.Adapter] lifecycle.
 * This "hook in" mechanism is provided by [AdapterDelegatesManager] and that is the
 * component
 * you have to use.
 *
 * @param <T> The type of the data source
 * @author Hannes Dorfmann
 * @since 1.0
</T> */
abstract class AdapterDelegate<T> {

    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Called to determine whether this AdapterDelegate is the responsible for the given data
     * element.
     *
     * @param item     The data source of the Adapter
     * @param position The position in the datasource
     * @return true, if this item is responsible,  otherwise false
     */
    internal abstract fun isForViewType(item: T, position: Int): Boolean


    /**
     * Creates the  [BindingHolder] for the given data source item
     *
     * @param parent The ViewGroup parent of the given datasource
     * @return The new instantiated [BindingHolder]
     */
    internal abstract fun onCreateViewHolder(parent: ViewGroup, listener: OnRecyclerItemClick<T>?): BindingHolder<ViewDataBinding>

    /**
     * Called to bind the [BindingHolder] to the item of the datas source set
     *
     * @param item   The data source
     * @param holder The [BindingHolder] to bind
     */
    open fun onBindViewHolder(item: T, holder: BindingHolder<ViewDataBinding>){

    }

    /**
     * Called to bind the [BindingHolder] to the item of the datas source set
     *
     * @param item     The data source
     * @param holder   The [BindingHolder] to bind
     * @param position The position in source
     */
    open fun onBindViewHolder(item: T, holder: BindingHolder<ViewDataBinding>, position: Int) {

    }


    /**
     * Called when a view created by this adapter has been recycled.
     *
     *
     *
     * A view is recycled when a [RecyclerView.LayoutManager] decides that it no longer
     * needs to be attached to its parent [RecyclerView]. This can be because it has
     * fallen out of visibility or a set of cached views represented by views still
     * attached to the parent RecyclerView. If an item view has large or expensive data
     * bound to it such as large bitmaps, this may be a good place to release those
     * resources.
     *
     *
     * RecyclerView calls this method right before clearing ViewHolder's internal data and
     * sending it to RecycledViewPool. This way, if ViewHolder was holding valid information
     * before being recycled, you can call [BindingHolder.getAdapterPosition] to
     * get
     * its adapter position.
     *
     * @param viewHolder The ViewHolder for the view being recycled
     */
    internal open fun onViewRecycled(viewHolder: BindingHolder<ViewDataBinding>){

    }

    /**
     * Called by the RecyclerView if a ViewHolder created by this Adapter cannot be recycled
     * due to its transient state. Upon receiving this callback, Adapter can clear the
     * animation(s) that effect the View's transient state and return `true` so that
     * the View can be recycled. Keep in mind that the View in question is already removed from
     * the RecyclerView.
     *
     *
     * In some cases, it is acceptable to recycle a View although it has transient state. Most
     * of the time, this is a case where the transient state will be cleared in
     * [RecyclerView.Adapter.onBindViewHolder] (BindingHolder, int)} call when View is
     * rebound to a new position.
     * For this reason, RecyclerView leaves the decision to the Adapter and uses the return
     * value of this method to decide whether the View should be recycled or not.
     *
     *
     * Note that when all animations are created by [RecyclerView.ItemAnimator], you
     * should never receive this callback because RecyclerView keeps those Views as children
     * until their animations are complete. This callback is useful when children of the item
     * views create animations which may not be easy to implement using an [ ].
     *
     *
     * You should *never* fix this issue by calling
     * `holder.itemView.setHasTransientState(false);` unless you've previously called
     * `holder.itemView.setHasTransientState(true);`. Each
     * `View.setHasTransientState(true)` call must be matched by a
     * `View.setHasTransientState(false)` call, otherwise, the state of the View
     * may become inconsistent. You should always prefer to end or cancel animations that are
     * triggering the transient state instead of handling it manually.
     *
     * @param holder The ViewHolder containing the View that could not be recycled due to its
     * transient state.
     * @return True if the View should be recycled, false otherwise. Note that if this method
     * returns `true`, RecyclerView *will ignore* the transient state of
     * the View and recycle it regardless. If this method returns `false`,
     * RecyclerView will check the View's transient state again before giving a final decision.
     * Default implementation returns false.
     */
    internal fun onFailedToRecycleView(holder: BindingHolder<ViewDataBinding>): Boolean {
        return false
    }

    /**
     * Called when a view created by this adapter has been attached to a window.
     *
     *
     *
     * This can be used as a reasonable signal that the view is about to be seen
     * by the user. If the adapter previously freed any resources in
     * [RecyclerView.Adapter.onViewDetachedFromWindow] (BindingHolder)
     * onViewDetachedFromWindow}
     * those resources should be restored here.
     *
     * @param holder Holder of the view being attached
     */
    internal fun onViewAttachedToWindow(holder: BindingHolder<ViewDataBinding>) {}

    /**
     * Called when a view created by this adapter has been detached from its window.
     *
     *
     *
     * Becoming detached from the window is not necessarily a permanent condition;
     * the consumer of an Adapter's views may choose to cache views offscreen while they
     * are not visible, attaching an detaching them as appropriate.
     *
     * @param holder Holder of the view being detached
     */
    internal fun onViewDetachedFromWindow(holder: BindingHolder<ViewDataBinding>) {}

    protected fun inflateViewDataBinding(parent: ViewGroup): ViewDataBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
    }
}