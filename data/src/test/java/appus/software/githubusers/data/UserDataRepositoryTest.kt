package appus.software.githubusers.data

import appus.software.githubusers.data.net.GitHubAPI
import appus.software.githubusers.data.net.RetrofitFactory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject

@RunWith(JUnit4::class)
class UserDataRepositoryTest: KoinComponent {
    companion object {
        private const val USER_NAME = "USER"
        private const val NOT_EXIST_USER_NAME = "USERUSERUSER7777"
    }

    val gitHubAPI by inject<GitHubAPI>()

    @Before
    fun setUp() {
        StandAloneContext.startKoin(listOf(module { single { RetrofitFactory.getGitHubApi() } }))
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getUserAPITest() {
        gitHubAPI.getUserData(USER_NAME)
            .test()
            .assertNoErrors()
    }

    @Test
    fun getUserSubscribedTest() {
        gitHubAPI.getUserData(USER_NAME)
            .test()
            .assertSubscribed()
    }

    @Test
    fun getContributorsEmptyTest() {
        gitHubAPI.getUserData(NOT_EXIST_USER_NAME)
            .test()
            .assertFailure(RuntimeException::class.java)
    }

}