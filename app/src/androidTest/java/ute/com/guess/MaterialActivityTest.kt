package ute.com.guess

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)

    @Test
    fun guessWrong() {
        val resources = activityTestRule.activity.resources
        val secret = activityTestRule.activity.secretNumber.secret
        for (n in 1..10) {
            if (n != secret) {
                onView(withId(R.id.ed_number)).perform(clearText())//清除
                onView(withId(R.id.ed_number)).perform(typeText(n.toString()))//針對ed_number元件輸入值
                onView(withId(R.id.btn_ok)).perform(click())//按按鈕

                val message =
                    if (n < secret) resources.getString(R.string.highter)
                    else resources.getString(R.string.lower)
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resources.getString(R.string.ok))).perform(click())
            }
        }
    }

    @Test
    fun guessCounterReset(){
        val resources = activityTestRule.activity.resources
        onView(withId(R.id.fab)).perform(click())//按按鈕
        onView(withText(resources.getString(R.string.ok))).perform(click())
    }
}