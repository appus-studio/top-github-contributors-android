package appus.software.githubusers.presentation.views.base.model
import androidx.annotation.StringRes

data class ToastModel constructor(var message: String? = null, @StringRes var idResMessage: Int? = null)
