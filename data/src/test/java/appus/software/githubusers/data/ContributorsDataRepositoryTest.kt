package appus.software.githubusers.data

import appus.software.githubusers.data.entities.ContributorEntity
import appus.software.githubusers.data.net.GitHubAPI
import appus.software.githubusers.data.net.RetrofitFactory
import io.mockk.every
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.dsl.module.module
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject

@RunWith(JUnit4::class)
class ContributorsDataRepositoryTest : KoinComponent {
    companion object {
        private const val REPO_OWNER = "JetBrains"
        private const val NOT_EXIST_REPO_OWNER = "JetBrainsJetBrainsJetBrains"
        private const val REPO_NAME = "kotlin"
    }

    private val gitHubAPI by inject<GitHubAPI>()

    @Before
    fun before() {
        startKoin(listOf(module { single { RetrofitFactory.getGitHubApi() } }))
    }

    @After
    fun after() {
        stopKoin()
    }


    @Test
    fun getContributorsAPITest() {
        gitHubAPI.getContributors(REPO_OWNER, REPO_NAME)
            .test()
            .assertNoErrors()
    }

    @Test
    fun getContributorsSubscribedTest() {
        gitHubAPI.getContributors(REPO_OWNER, REPO_NAME)
            .test()
            .assertSubscribed()
    }

    @Test
    fun getContributorsEmptyTest() {
        gitHubAPI.getContributors(NOT_EXIST_REPO_OWNER, REPO_NAME)
            .test()
            .assertFailure(RuntimeException::class.java)
    }
}