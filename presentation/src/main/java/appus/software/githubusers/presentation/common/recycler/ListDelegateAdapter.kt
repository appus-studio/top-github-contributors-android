package appus.software.githubusers.presentation.common.recycler

import android.util.SparseArray
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Created by bogdan.martynov on 2019-04-25 12:14. top-github-contributors-android
 */

class ListDelegateAdapter<T> : RecyclerView.Adapter<BindingHolder<ViewDataBinding>> {


    private var mListener: OnRecyclerItemClick<T>? = null
    private var delegatesManager: AdapterDelegatesManager<T>? = null
    private var items: MutableList<T>? = null

    var data: MutableList<T>?
        get() = items
        set(data) {
            items = data
        }

    private constructor()

    constructor(builder: Builder<T>) {
        mListener = builder.getListener()
        delegatesManager = builder.getDelegatesManager()
        items = builder.getItems()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<ViewDataBinding> {
        return delegatesManager!!.onCreateViewHolder(parent, viewType, mListener)
    }

    override fun onBindViewHolder(holder: BindingHolder<ViewDataBinding>, position: Int) {
        delegatesManager!!.onBindViewHolder(items!![position], position, holder)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager!!.getItemViewType(items!![position], position)
    }

    override fun onViewRecycled(holder: BindingHolder<ViewDataBinding>) {
        delegatesManager!!.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: BindingHolder<ViewDataBinding>): Boolean {
        return delegatesManager!!.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: BindingHolder<ViewDataBinding>) {
        delegatesManager!!.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: BindingHolder<ViewDataBinding>) {
        delegatesManager!!.onViewDetachedFromWindow(holder)
    }

    /**
     * Get the items / data source of this adapter
     *
     * @return The items / data source
     */
    fun getItems(): List<T>? {
        return items
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    fun updateItems(models: List<T>?) {
        items = models?.let { ArrayList(it) } ?: ArrayList()
        notifyDataSetChanged()
    }

    @JvmOverloads
    fun updateItemByPosition(model: T?, position: Int, notifyChanged: Boolean = true) {
        if (getItems()!!.size >= position && position >= 0 && model != null) {
            items!![position] = model
            if (notifyChanged) {
                notifyItemChanged(position)
            }
        }
    }

    fun updateItemsByPosition(array: SparseArray<T>) {
        for (i in 0 until array.size()) {
            val position = array.keyAt(i)
            updateItemByPosition(array.get(position), position)
        }
    }

    fun removeItem(position: Int) {
        if (position >= 0 && position < items!!.size) {
            items!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removeItem(from: Int, count: Int) {
        if (from >= 0 && from + count <= items!!.size) {
            for (i in 0 until count)
                items!!.removeAt(from)
            notifyItemRangeRemoved(from, count)
        }
    }

    fun clear() {
        items!!.clear()
        notifyDataSetChanged()
    }

    fun addItem(item: T) {
        items!!.add(item)
        notifyItemRangeChanged(items!!.size - 1, 1)
    }

    fun addItem(position: Int, item: T) {
        if (position >= 0 && position < items!!.size) {
            items!!.add(position, item)
            notifyItemInserted(position)
        }
    }

    fun setItem(position: Int, item: T) {
        if (position >= 0 && position < items!!.size) {
            items!![position] = item
            notifyItemChanged(position)
        }
    }

    fun addItem(from: Int, models: List<T>?) {
        if (from >= 0 && from <= items!!.size && models != null) {
            //   for (int i = from; i <= models.size(); i++)
            items!!.addAll(from, models)
            notifyItemRangeInserted(from, models.size)
        }
    }

    fun getItem(position: Int): T? {
        return if (position >= 0 && position < itemCount) items!![position] else null
    }

    fun insertItems(data: List<T>, position: Int) {
        if (position >= 0 && items!!.size >= position && !data.isEmpty()) {
            items!!.addAll(position, data)
            notifyItemRangeInserted(position, data.size)
        }
    }

    fun deleteItems(position: Int, count: Int) {
        var lCount = count
        if (position >= 0 && position < items!!.size && position + lCount <= items!!.size) {
            while (lCount > 0) {
                items!!.removeAt(position)
                lCount--
            }
            notifyItemRangeRemoved(position, count)
        }
    }

    class Builder<T> {
        private var mListener: OnRecyclerItemClick<T>? = null
        private var delegatesManager: AdapterDelegatesManager<T>? = null
        private var items: MutableList<T>? = null

        init {
            delegatesManager = AdapterDelegatesManager()
            items = ArrayList()
        }

        fun getListener(): OnRecyclerItemClick<T>? {
            return mListener
        }

        fun setListener(listener: OnRecyclerItemClick<T>): Builder<T> {
            this.mListener = listener
            return this
        }

        fun getDelegatesManager(): AdapterDelegatesManager<T>? {
            return delegatesManager
        }

        fun setDelegatesManager(delegatesManager: AdapterDelegatesManager<T>): Builder<T> {
            this.delegatesManager = delegatesManager
            return this
        }

        fun getItems(): MutableList<T>? {
            return items
        }

        fun setItems(items: MutableList<T>): Builder<T> {
            this.items = items
            return this
        }

        fun addDelegate(item: AdapterDelegate<T>): Builder<T> {
            if (delegatesManager == null) {
                throw IllegalArgumentException("provide DelegateManager")
            }
            if (items == null) {
                throw IllegalArgumentException("provide AdapterDelegate<T> item")
            }
            delegatesManager!!.addDelegate(item)
            return this
        }

        fun build(): ListDelegateAdapter<T> {
            return ListDelegateAdapter(this)
        }
    }
}