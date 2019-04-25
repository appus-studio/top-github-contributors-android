package appus.software.githubusers.presentation.app.di

import appus.software.githubusers.data.repositories.ContributorsDataRepository
import appus.software.githubusers.data.repositories.UserDataRepository
import appus.software.githubusers.domain.repository.ContributorsRepository
import appus.software.githubusers.domain.repository.UserRepository
import org.koin.dsl.module.module


/**
 * Created by bogdan.martynov on 2019-04-24 18:16. top-github-contributors-android
 */


/**
 * Module for injection data components
 */
val dataModule = module {
    single<ContributorsRepository> { ContributorsDataRepository(get()) }
    single<UserRepository> { UserDataRepository(get()) }
}
