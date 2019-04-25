package appus.software.githubusers.presentation.views

import appus.software.githubusers.R
import appus.software.githubusers.databinding.ActivityMainBinding
import appus.software.githubusers.presentation.views.base.BaseActivity

/**
 * Created by bogdan.martynov on 2019-04-24 15:59. top-github-contributors-android
 */

class MainView: BaseActivity<ActivityMainBinding, MainVM>(MainVM::class) {
    override val layoutId: Int = R.layout.activity_main
}