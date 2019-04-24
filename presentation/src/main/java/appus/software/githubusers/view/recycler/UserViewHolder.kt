package appus.software.githubusers.view.recycler

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import appus.software.githubusers.R
import kotlinx.android.synthetic.main.item_contributor.view.*

/**
 * Created by bogdan.martynov on 4/12/19 1:32 PM. gitHubUsers
 */


// ViewHolder Pattern
class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val container: LinearLayout
    val ivAvatar: ImageView
    val tvName: TextView
    val tvCommitsCount: TextView

    init {
        container = view.findViewById(R.id.container)
        ivAvatar = view.findViewById(R.id.ivAvatar)
        tvName = view.findViewById(R.id.tvName)
        tvCommitsCount = view.findViewById(R.id.tvCommitsCount)
    }
}