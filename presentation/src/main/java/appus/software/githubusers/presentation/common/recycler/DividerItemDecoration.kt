package appus.software.githubusers.presentation.common.recycler

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by bogdan.martynov on 2019-04-25 12:17. top-github-contributors-android
 */

class DividerItemDecoration
/**
 * Sole constructor. Takes in a [Drawable] to be used as the interior
 * divider.
 *
 * @param divider A divider `Drawable` to be drawn on the RecyclerView
 */
    (private val mDivider: Drawable, private val withLastDivider: Boolean) : RecyclerView.ItemDecoration() {
    private var mOrientation: Int = 0

    /**
     * Draws horizontal or vertical dividers onto the parent RecyclerView.
     *
     * @param canvas The [Canvas] onto which dividers will be drawn
     * @param parent The RecyclerView onto which dividers are being added
     * @param state  The current RecyclerView.State of the RecyclerView
     */
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontalDividers(canvas, parent)
        } else if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVerticalDividers(canvas, parent)
        }
    }

    /**
     * Determines the size and location of offsets between items in the parent
     * RecyclerView.
     *
     * @param outRect The [Rect] of offsets to be added around the child
     * view
     * @param view    The child view to be decorated with an offset
     * @param parent  The RecyclerView onto which dividers are being added
     * @param state   The current RecyclerView.State of the RecyclerView
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0) {
            return
        }

        mOrientation = (parent.layoutManager as LinearLayoutManager).orientation
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            outRect.left = mDivider.intrinsicWidth
        } else if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.top = mDivider.intrinsicHeight
        }
    }

    /**
     * Adds dividers to a RecyclerView with a LinearLayoutManager or its
     * subclass oriented horizontally.
     *
     * @param canvas The [Canvas] onto which horizontal dividers will be
     * drawn
     * @param parent The RecyclerView onto which horizontal dividers are being
     * added
     */
    private fun drawHorizontalDividers(canvas: Canvas, parent: RecyclerView) {
        val parentTop = parent.paddingTop
        val parentBottom = parent.height - parent.paddingBottom

        val childCount = parent.childCount
        for (i in 0 until childCount - if (withLastDivider) 0 else 1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val parentLeft = child.right + params.rightMargin
            val parentRight = parentLeft + mDivider.intrinsicWidth

            mDivider.setBounds(parentLeft, parentTop, parentRight, parentBottom)
            mDivider.draw(canvas)
        }
    }

    /**
     * Adds dividers to a RecyclerView with a LinearLayoutManager or its
     * subclass oriented vertically.
     *
     * @param canvas The [Canvas] onto which vertical dividers will be
     * drawn
     * @param parent The RecyclerView onto which vertical dividers are being
     * added
     */
    private fun drawVerticalDividers(canvas: Canvas, parent: RecyclerView) {
        val parentLeft = parent.paddingLeft
        val parentRight = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount - if (withLastDivider) 0 else 1) {
                val child = parent.getChildAt(i)

                val params = child.layoutParams as RecyclerView.LayoutParams

                val parentTop = child.bottom + params.bottomMargin
                val parentBottom = parentTop + mDivider.intrinsicHeight

                mDivider.setBounds(parentLeft, parentTop, parentRight, parentBottom)
                mDivider.draw(canvas)
        }
    }
}