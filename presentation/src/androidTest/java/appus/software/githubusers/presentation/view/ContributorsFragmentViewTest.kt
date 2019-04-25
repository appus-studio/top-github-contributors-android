package appus.software.githubusers.presentation.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import appus.software.githubusers.R
import appus.software.githubusers.presentation.views.MainView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by bogdan.martynov on 2019-04-25 16:58. top-github-contributors-android
 */

@RunWith(AndroidJUnit4::class)
class ContributorsFragmentViewTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainView> = ActivityTestRule(MainView::class.java)

    @Test
    fun recycler_isDisplayedTest() {
        onView((withId(R.id.recycler))).check(matches(isDisplayed()))
    }


    @Test
    fun tapOnUserTest() {
        Thread.sleep(3500)
        onView(withId(R.id.recycler)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
    }
}