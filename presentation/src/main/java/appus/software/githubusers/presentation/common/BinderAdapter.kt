package appus.software.githubusers.presentation.common

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import appus.software.githubusers.domain.model.field.Field
import appus.software.githubusers.domain.model.field.FieldAction
import appus.software.githubusers.presentation.common.recycler.ListConfig
import appus.software.githubusers.presentation.common.recycler.OnRecyclerItemClick
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by bogdan.martynov on 2019-04-25 12:48. top-github-contributors-android
 */

@BindingAdapter("actionHandler", "data", "action", "position")
fun fieldActionClick(view: View,
                     actionHandler: OnRecyclerItemClick<Field>,
                     data: Field,
                     action: FieldAction,
                     position: Int) {
    view.setOnClickListener {
        data.fieldAction = action
        actionHandler.onItemClick(data, position)
    }
}

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String?, error: Drawable) {
    Glide.with(view.context)
            .asBitmap()
            .load(url)
            .apply(RequestOptions().error(error))
            .into(view)
}

@BindingAdapter("listConfig")
fun configRecyclerView(recyclerView: RecyclerView, configBuilder: ListConfig.Builder<Field>?) {
    if (configBuilder == null) return
    val config = configBuilder.build(recyclerView.context)
    config.applyConfig(recyclerView.context, recyclerView)
}
