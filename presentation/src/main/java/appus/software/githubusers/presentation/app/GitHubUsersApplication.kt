package appus.software.githubusers.presentation.app

import android.app.Application
import appus.software.githubusers.presentation.app.di.*
import org.koin.android.ext.android.startKoin
import org.koin.log.EmptyLogger

/**
 * Created by bogdan.martynov on 2019-04-24 17:44. top-github-contributors-android
 */

class GitHubUsersApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            appModule,
            useCasesModule,
            dataModule,
            netModule,
            viewModelModule
        ), logger = EmptyLogger())
    }
}