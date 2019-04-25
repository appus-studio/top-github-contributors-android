package appus.software.githubusers.presentation.common.recycler

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import appus.software.githubusers.R
import java.util.*

/**
 * Created by bogdan.martynov on 2019-04-25 11:44. top-github-contributors-android
 */

class ListConfig private constructor(
    private val mAdapter: RecyclerView.Adapter<BindingHolder<ViewDataBinding>>,
    private val mLayoutManagerProvider: LayoutManagerProvider?,
    private val mItemAnimator: RecyclerView.ItemAnimator?,
    itemDecorations: List<RecyclerView.ItemDecoration>?,
    scrollListeners: List<RecyclerView.OnScrollListener>?,
    private val mItemTouchHelper: ItemTouchHelper?,
    private val mHasFixedSize: Boolean,
    private val layoutPosition: Int
) {
    private val mItemDecorations: List<RecyclerView.ItemDecoration>
    private val mScrollListeners: List<RecyclerView.OnScrollListener>

    init {
        mItemDecorations = itemDecorations ?: emptyList()
        mScrollListeners = scrollListeners ?: emptyList()
    }

    /**
     * Applies defined configuration for RecyclerView
     *
     * @param context      the context
     * @param recyclerView the target recycler view for applying the configuration
     */
    fun applyConfig(context: Context, recyclerView: RecyclerView) {
        if (mLayoutManagerProvider == null || mAdapter == null) return
        val layoutManager: RecyclerView.LayoutManager = mLayoutManagerProvider[context]

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(mHasFixedSize)
        recyclerView.adapter = mAdapter
        recyclerView.scrollToPosition(layoutPosition)

        for (i in 0 until recyclerView.itemDecorationCount) {
            recyclerView.removeItemDecorationAt(i)
        }
        if (!mItemDecorations.isEmpty()) {
            for (item in mItemDecorations) {
                recyclerView.addItemDecoration(item)
            }
        }

        for (scrollListener in mScrollListeners) {
            recyclerView.addOnScrollListener(scrollListener)
        }

        if (mItemAnimator != null) {
            recyclerView.itemAnimator = mItemAnimator
        }
        mItemTouchHelper?.attachToRecyclerView(recyclerView)
    }

    /**
     * Builder for setting ListConfig
     * Sample:
     * <pre>
     * `ListConfig listConfig = new ListConfig.Builder(mAdapter)
     * .setLayoutManagerProvider(new SimpleGridLayoutManagerProvider(mSpanCount, getSpanSizeLookup()))
     * .addItemDecoration(new ColorDividerItemDecoration(color, spacing, SPACE_LEFT|SPACE_TOP, false))
     * .setDefaultDividerEnabled(true)
     * .addOnScrollListener(new OnLoadMoreScrollListener(mCallback))
     * .setItemAnimator(getItemAnimator())
     * .setHasFixedSize(true)
     * .setItemTouchHelper(getItemTouchHelper())
     * .build(context);
    ` *
    </pre> *
     * If LinearLayoutManager will be used by default
     */
    class Builder<T>
    /**
     * Creates new Builder for config RecyclerView with the adapter
     *
     * @param adapter the adapter, which will be set to the RecyclerView
     */
        (val adapter: RecyclerView.Adapter<BindingHolder<ViewDataBinding>>) {
        private var mLayoutManagerProvider: LayoutManagerProvider? = null
        private var mItemAnimator: RecyclerView.ItemAnimator? = null
        private val mItemDecorationsProviders: MutableList<ItemDecorationProvider>
        private var mOnScrollListeners: MutableList<RecyclerView.OnScrollListener>? = null
        private var mItemTouchHelper: ItemTouchHelper? = null
        private val mHasFixedSize: Boolean = false
        private var hasDivider: Boolean = false
        private var layoutPosition: Int = 0

        val listDelegateAdapter: ListDelegateAdapter<T>
            get() = adapter as ListDelegateAdapter<T>

        init {
            mItemDecorationsProviders = ArrayList()
        }

        /**
         * Set Layout manager provider. If not set default [LinearLayoutManager] will be applied
         *
         * @param layoutManagerProvider the layout manager provider. Can be custom or one of
         * simple: [SimpleLinearLayoutManagerProvider]
         * @return the builder
         */
        fun setLayoutManagerProvider(layoutManagerProvider: LayoutManagerProvider): Builder<T> {
            mLayoutManagerProvider = layoutManagerProvider
            return this
        }

        fun getLayoutManagerProvider(): LayoutManagerProvider? {
            return mLayoutManagerProvider
        }

        /**
         * Set [RecyclerView.ItemAnimator]
         *
         * @param itemAnimator the item animator
         * @return the builder
         */
        fun setItemAnimator(itemAnimator: RecyclerView.ItemAnimator): Builder<T> {
            mItemAnimator = itemAnimator
            return this
        }

        /**
         * Set [RecyclerView.ItemDecoration]
         *
         * @param itemDecoration the item decoration. Can be set any custom item decoration
         * @return the builder
         */
        fun addItemDecoration(itemDecoration: ItemDecorationProvider): Builder<T>{
            mItemDecorationsProviders.add(itemDecoration)
            hasDivider = true
            return this
        }

        /**
         * Set [RecyclerView.OnScrollListener]
         *
         * @param onScrollListener the scroll listener. Can be set any custom or used one of
         * @return the builder
         */
        fun addOnScrollListener(onScrollListener: RecyclerView.OnScrollListener): Builder<T>{
            if (mOnScrollListeners == null) {
                mOnScrollListeners = ArrayList<RecyclerView.OnScrollListener>()
            }
            mOnScrollListeners!!.add(onScrollListener)
            return this
        }

        /**
         * Set true to apply default divider with default size of 4dp.
         *
         * @param isEnabled set true to apply default divider.
         * @return the builder
         */
        fun setDefaultDividerEnabled(isEnabled: Boolean): Builder<T>{
            hasDivider = isEnabled
            mItemDecorationsProviders.add(SimpleItemDecorationProvider(R.drawable.divider, true))
            return this
        }

        /**
         * Set [ItemTouchHelper]
         *
         * @param itemTouchHelper the ItemTouchHelper to apply for RecyclerView
         * @return the builder
         */
        fun setItemTouchHelper(itemTouchHelper: ItemTouchHelper): Builder<T>{
            mItemTouchHelper = itemTouchHelper
            return this
        }

        fun setLayoutPosition(position: Int): Builder<T>{
            this.layoutPosition = position
            return this
        }

        /**
         * Creates new [ListConfig] with defined configuration
         * If LayoutManagerProvider is not set, the [SimpleLinearLayoutManagerProvider]
         * will be used.
         *
         * @param context the context
         * @return the new ListConfig
         */
        fun build(context: Context): ListConfig {
            if (mLayoutManagerProvider == null)
                mLayoutManagerProvider = SimpleLinearLayoutManagerProvider()

            val itemDecorations = ArrayList<RecyclerView.ItemDecoration>()

            if (!mItemDecorationsProviders.isEmpty()) {
                for (item in mItemDecorationsProviders)
                    itemDecorations.add(item[context])
            }

            return ListConfig(
                adapter,
                mLayoutManagerProvider,
                mItemAnimator,
                itemDecorations,
                mOnScrollListeners,
                mItemTouchHelper,
                mHasFixedSize,
                layoutPosition
            )
        }
    }

    /**
     * The provider of LayoutManager for RecyclerView
     */
    interface LayoutManagerProvider {
        operator fun get(context: Context): RecyclerView.LayoutManager
    }

    /**
     * The provider of LayoutManager for RecyclerView
     */
    interface ItemDecorationProvider {
        operator fun get(context: Context): RecyclerView.ItemDecoration

        interface DecorationRule {
            fun isNeedToDecorate(position: Int): Boolean
        }
    }


    /**
     * The provider of LayoutManager for RecyclerView
     */
    class SimpleItemDecorationProvider(private val resId: Int, private val withLastDivider: Boolean) :
        ItemDecorationProvider {

        override fun get(context: Context): RecyclerView.ItemDecoration {
            return DividerItemDecoration(ContextCompat.getDrawable(context, resId)!!, withLastDivider)
        }
    }


    /**
     * The simple LayoutManager provider for [LinearLayoutManager]
     */
    class SimpleLinearLayoutManagerProvider : LayoutManagerProvider {
        private val orientation: Int
        private val reverse: Boolean

        constructor() {
            this.orientation = LinearLayoutManager.VERTICAL
            this.reverse = false
        }

        constructor(orientation: Int, reverse: Boolean) {
            this.orientation = orientation
            this.reverse = reverse
        }


        override fun get(context: Context): RecyclerView.LayoutManager {
            return LinearLayoutManager(context, orientation, reverse)
        }
    }
}