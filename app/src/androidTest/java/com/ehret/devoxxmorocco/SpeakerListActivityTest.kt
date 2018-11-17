package com.ehret.devoxxmorocco

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.ehret.devoxxmorocco.model.AppDatabase
import com.ehret.devoxxmorocco.model.Speaker
import com.ehret.devoxxmorocco.model.SpeakerAdapater
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SpeakerListActivityTest{

    class MyActivityWithDbRule : ActivityTestRule<SpeakerListActivity>(SpeakerListActivity::class.java) {
        private lateinit var database: AppDatabase

         override fun beforeActivityLaunched() {
            database = AppDatabase.instance(InstrumentationRegistry.getInstrumentation().targetContext, true)
            database.speakerDao().create(Speaker("Guillaume", "EHRET","12"))
            database.speakerDao().create(Speaker("Romain", "GUY", "13"))
        }

        override fun afterActivityFinished() {
            database.close()
        }
    }

    @get:Rule
    val activityRule = MyActivityWithDbRule()

    @Test
    fun shouldDisplayMySpeakers() {
        Espresso.onView(ViewMatchers.withText("Guillaume EHRET")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("Romain GUY")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldCreateNewSpeaker() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonAddSpeaker)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.speakerFirstname)).check(ViewAssertions.matches(ViewMatchers.withText("")))
        Espresso.onView(ViewMatchers.withId(R.id.speakerLastname)).check(ViewAssertions.matches(ViewMatchers.withText("")))
    }

    @Test
    fun shouldUpdateSpeaker() {
        Espresso.onView(ViewMatchers.withId(R.id.speakerList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<SpeakerAdapater.ViewHolder>(1, ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(R.id.speakerFirstname)).check(ViewAssertions.matches(ViewMatchers.withText("Romain")))
        Espresso.onView(ViewMatchers.withId(R.id.speakerLastname)).check(ViewAssertions.matches(ViewMatchers.withText("GUY")))
    }
}