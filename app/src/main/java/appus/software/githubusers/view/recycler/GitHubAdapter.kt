package appus.software.githubusers.view.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import appus.software.githubusers.R
import appus.software.githubusers.model.ContributorModel
import com.bumptech.glide.Glide

/**
 * Created by bogdan.martynov on 4/12/19 1:32 PM. gitHubUsers
 */

class GitHubAdapter(
    private val context: Context,
    private val list: List<ContributorModel>,
    private val clickListener: ClickListener<ContributorModel>
) : RecyclerView.Adapter<UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val contributor = list[position]

        holder.container.setOnClickListener {
            clickListener.clicked(contributor)
        }

        //Glide - An image loading and caching library
        Glide.with(context)
            .asBitmap()
            .load(contributor.author.avatar_url)
            .into(holder.ivAvatar)

        holder.tvName.text = contributor.author.login
        holder.tvCommitsCount.text = contributor.total.toString()
    }


}