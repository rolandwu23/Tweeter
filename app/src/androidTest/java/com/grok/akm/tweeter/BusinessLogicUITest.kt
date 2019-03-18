package com.grok.akm.tweeter

import android.util.Log
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class BusinessLogicUITest {


    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule<TweeterActivity>(TweeterActivity::class.java)

    companion object {

        private val test1 =
            "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        private val test2 = "                                                  "
        private val test3 = ""
        private val test4 = "Ican'tbelieveTweeternowsupportschunkingmymessages,soIdonot"

        private val ans1_test1 = "1/2 I can't believe Tweeter now supports chunking"
        private val ans2_test1 = "2/2 my messages, so I don't have to do it myself."

    }

    @Test
    fun Test1(){

        Log.e("@Test","Performing Test 1")

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_input_TextInputEditText))
            .perform(ViewActions.typeText(test1), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_send_button)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.answerActivity_listview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onData(CoreMatchers.anything())
            .inAdapterView(ViewMatchers.withId(R.id.answerActivity_listview))
            .atPosition(0)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        Espresso.onData(CoreMatchers.anything())
            .inAdapterView(ViewMatchers.withId(R.id.answerActivity_listview))
            .atPosition(1)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onData(CoreMatchers.anything())
            .inAdapterView(ViewMatchers.withId(R.id.answerActivity_listview))
            .atPosition(0)
            .onChildView(ViewMatchers.withId(R.id.card_view_ans_text))
            .check(ViewAssertions.matches(ViewMatchers.withText(ans1_test1)));

        Espresso.onData(CoreMatchers.anything())
            .inAdapterView(ViewMatchers.withId(R.id.answerActivity_listview))
            .atPosition(1)
            .onChildView(ViewMatchers.withId(R.id.card_view_ans_text))
            .check(ViewAssertions.matches(ViewMatchers.withText(ans2_test1)));

    }

    @Test
    fun Test2(){

        Log.e("@Test","Performing Test 2")

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_input_TextInputEditText))
            .perform(ViewActions.typeText(test2), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_send_button)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText(ConstantCommons.ALL_SPACE_ERROR_MSG))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_input_TextInputLayout))
            .check(ViewAssertions.matches(CustomMatchers.hasTextInputLayoutHintText("Cannot be all space")))

    }

    @Test
    fun Test3(){

        Log.e("@Test","Performing Test 3")

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_input_TextInputEditText))
            .perform(ViewActions.typeText(test3), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_send_button)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText(ConstantCommons.ZERO_LENGTH_ERROR_MSG))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_input_TextInputLayout))
            .check(ViewAssertions.matches(CustomMatchers.hasTextInputLayoutHintText(ConstantCommons.ZERO_LENGTH_ERROR_MSG)))

    }

    @Test
    fun Test4(){

        Log.e("@Test","Performing Test 4")

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_input_TextInputEditText))
            .perform(ViewActions.typeText(test4), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_send_button)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText(ConstantCommons.CASE_ERROR_MSG))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.activity_twitsplit_input_TextInputLayout))
            .check(ViewAssertions.matches(CustomMatchers.hasTextInputLayoutHintText(ConstantCommons.CASE_ERROR_MSG)))

    }
}