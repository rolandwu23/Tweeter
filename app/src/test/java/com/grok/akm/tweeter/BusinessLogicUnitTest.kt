package com.grok.akm.tweeter

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class BusinessLogicUnitTest {

    companion object {

        // For TwitSplitActivity
        private val test1 = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        private val test2 = ""
        private val test3 = "                                                  "
        private val test4 = "Ican'tbelieveTweeternowsupportschunkingmymessages,soIdonot"
        private val test5 = "I can't believe Tweeter now supports chunking my messages, so I don't have"

        private val ans1 = 1
        private val ans2 = -1
        private val ans3 = -2
        private val ans4 = -3

        // For Answer Activity
        private val ans1_test1 = "I can't believe Tweeter now supports chunking"
        private val ans2_test1 = "my messages, so I don't have to do it myself."
        private val ans1_test5 = "I can't believe Tweeter now supports chunking"
        private val ans2_test5 = "my messages, so I don't have"
     }

    @Test
    fun Test1() {
        val twitSplitActivity = TweeterActivity()
        val result = twitSplitActivity.validateString(test1)
        MatcherAssert.assertThat(result, CoreMatchers.`is`(ans1))

        val answerActivity = AnswerActivity()
        val arrayList = answerActivity.splitString(test1, 46)
        MatcherAssert.assertThat(arrayList[0], CoreMatchers.`is`(ans1_test1))
        MatcherAssert.assertThat(arrayList[1], CoreMatchers.`is`(ans2_test1))

    }

    @Test
    fun Test2() {
        val twitSplitActivity = TweeterActivity()
        val result = twitSplitActivity.validateString(test2)
        MatcherAssert.assertThat(result, CoreMatchers.`is`(ans2))
    }

    @Test
    fun Test3() {
        val twitSplitActivity = TweeterActivity()
        val result = twitSplitActivity.validateString(test3)
        MatcherAssert.assertThat(result, CoreMatchers.`is`(ans3))
    }

    @Test
    fun Test4() {
        val twitSplitActivity = TweeterActivity()
        val result = twitSplitActivity.validateString(test4)
        MatcherAssert.assertThat(result, CoreMatchers.`is`(ans4))
    }

    @Test
    fun Test5() {
        val twitSplitActivity = TweeterActivity()
        val result = twitSplitActivity.validateString(test5)
        MatcherAssert.assertThat(result, CoreMatchers.`is`(ans1))

        val answerActivity = AnswerActivity()
        val arrayList = answerActivity.splitString(test5, 46)
        MatcherAssert.assertThat(arrayList[0], CoreMatchers.`is`(ans1_test5))
        MatcherAssert.assertThat(arrayList[1], CoreMatchers.`is`(ans2_test5))
    }

}
