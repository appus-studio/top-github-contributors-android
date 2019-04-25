package appus.software.githubusers.presentation.views.base.model

import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

data class NavigationModel(
    var actionId: Int = 0,
    var bundle: Bundle? = null,
    var direction: NavDirections? = null,
    var popTo: Int? = null,
    var inclusive: Boolean = false,
    var singleTop: Boolean = false,
    val popBack: Boolean = false
) {

    val navOptions: NavOptions?
        get() = when {
            popTo != null -> NavOptions.Builder().setLaunchSingleTop(singleTop).setPopUpTo(popTo!!, inclusive).build()
            else -> null
        }

}
