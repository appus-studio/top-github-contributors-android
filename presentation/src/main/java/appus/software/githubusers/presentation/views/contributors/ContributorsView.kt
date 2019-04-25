package appus.software.githubusers.presentation.views.contributors

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import appus.software.githubusers.R
import appus.software.githubusers.databinding.FragmentContributorsBinding
import appus.software.githubusers.presentation.views.base.BaseFragment

/**
 * Created by bogdan.martynov on 2019-04-24 16:00. top-github-contributors-android
 */

class ContributorsView : BaseFragment<FragmentContributorsBinding, ContributorsVM>(ContributorsVM::class) {
    override val layoutId: Int = R.layout.fragment_contributors

    override fun initViews() {
        vm.showUserLocation.observe(this, Observer(this::goToMap))
    }

    private fun goToMap(location: String){
        val gmmIntentUri = Uri.parse("geo:0,0?q=$location")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}