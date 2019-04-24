package appus.software.githubusers.presentation.app

import android.app.Application
import appus.software.githubusers.presentation.app.di.appModule
import appus.software.githubusers.presentation.app.di.dataModule
import appus.software.githubusers.presentation.app.di.netModule
import appus.software.githubusers.presentation.app.di.useCasesModule
import org.koin.android.ext.android.startKoin
import org.koin.log.EmptyLogger

/**
 * Created by bogdan.martynov on 2019-04-24 17:44. top-github-contributors-android
 */

class GitHubUsersApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, useCasesModule, dataModule, netModule), logger = EmptyLogger())
    }
}