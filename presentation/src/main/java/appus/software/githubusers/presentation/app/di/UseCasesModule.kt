package appus.software.githubusers.presentation.app.di

import appus.software.githubusers.domain.interactors.GetContributorsUseCase
import appus.software.githubusers.domain.interactors.GetUserUseCase
import org.koin.dsl.module.module

/**
 * Created by bogdan.martynov on 2019-04-24 18:33. top-github-contributors-android
 */


/**
 * Module for injection useCases
 */
val useCasesModule = module {
    single { GetContributorsUseCase(get(), get(), get()) }
    single { GetUserUseCase(get(), get(), get()) }
}
