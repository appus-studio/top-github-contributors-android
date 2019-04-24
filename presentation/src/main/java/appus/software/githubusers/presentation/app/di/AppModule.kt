package appus.software.githubusers.presentation.app.di

import appus.software.githubusers.data.executors.JobExecutor
import appus.software.githubusers.domain.executor.PostExecutionThread
import appus.software.githubusers.domain.executor.ThreadExecutor
import appus.software.githubusers.presentation.app.UIThread
import org.koin.dsl.module.module

/**
 * Created by bogdan.martynov on 2019-04-24 18:15. top-github-contributors-android
 */

val appModule = module {
    single<PostExecutionThread> { UIThread() }
    single<ThreadExecutor> { JobExecutor() }
}