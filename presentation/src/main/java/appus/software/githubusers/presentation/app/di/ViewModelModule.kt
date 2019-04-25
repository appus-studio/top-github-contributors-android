package appus.software.githubusers.presentation.app.di

import appus.software.githubusers.presentation.views.MainVM
import appus.software.githubusers.presentation.views.contributors.ContributorsVM
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by bogdan.martynov on 2019-04-25 11:19. top-github-contributors-android
 */


val viewModelModule = module{
    viewModel { MainVM() }
    viewModel { ContributorsVM(get(), get()) }
}