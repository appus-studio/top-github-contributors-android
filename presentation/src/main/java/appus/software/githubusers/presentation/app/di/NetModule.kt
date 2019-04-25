package appus.software.githubusers.presentation.app.di

import appus.software.githubusers.data.net.RetrofitFactory
import org.koin.dsl.module.module


/**
 * Created by bogdan.martynov on 2019-04-24 17:42. top-github-contributors-android
 */

val netModule = module {
    single { RetrofitFactory.getGitHubApi() }
}
