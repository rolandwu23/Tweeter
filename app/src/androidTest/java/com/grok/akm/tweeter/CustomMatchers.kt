package com.grok.akm.tweeter

import android.support.design.widget.TextInputLayout
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object CustomMatchers {

    fun hasTextInputLayoutHintText(expectedErrorText: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {

            public override fun matchesSafely(view: View): Boolean {
                if (view !is TextInputLayout) {
                    return false
                }

                val error = view.error ?: return false

                val hint = error.toString()

                return expectedErrorText == hint
            }

            override fun describeTo(description: Description) {}
        }
    }
}
