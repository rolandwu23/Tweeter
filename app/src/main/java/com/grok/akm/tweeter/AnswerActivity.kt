package com.grok.akm.tweeter

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.grok.akm.tweeter.ConstantCommons.ACTION_BAR_HOME_BUTTON
import com.grok.akm.tweeter.ConstantCommons.INTEXT_XTRAS_TWEETERS
import kotlinx.android.synthetic.main.activity_answer.*
import java.util.*

class AnswerActivity : AppCompatActivity() {

    companion object {
        private const val ON_SAVED_INSTANCE_STATE_VALUE = "ON_SAVED_INSTANCE_STATE_VALUE"
    }

    private var storeString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        supportActionBar?.title = ACTION_BAR_HOME_BUTTON

        val text:String

        if(savedInstanceState != null)
        {
            text = savedInstanceState.getString(ON_SAVED_INSTANCE_STATE_VALUE,"")

        }else {
            text = intent.getStringExtra(INTEXT_XTRAS_TWEETERS)
        }
        storeString = text


        // Based on Android Version, setting the list view divider to be transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            answerActivity_listview.divider = resources.getDrawable(android.R.color.transparent,applicationContext.theme)
        } else {
            answerActivity_listview.divider = resources.getDrawable(android.R.color.transparent)
        }

        if(text.length <=50)
        {
            val tweets = splitString(text,50)
            val mAdapter = TweeterAdapter(this,tweets)
            answerActivity_listview.adapter = mAdapter
        }else {
            val width = lineWidth(text.length)
            val tweets = splitString(text, width)
            val mAdapter = TweeterAdapter(this, tweets)
            answerActivity_listview.adapter = mAdapter
        }
    }

    fun splitString(text: String, maxWidth: Int): ArrayList<String> {
        val words = text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val result = ArrayList<String>()

        if (words.isEmpty()) {
            return result
        }


        var count = 0
        var last = 0
        for (i in words.indices) {
            count += words[i].length

            if (count + i - last > maxWidth) {
                val wordsLen = count - words[i].length
                val spaceLen = maxWidth - wordsLen
                var eachLen = 1

                if (i - last - 1 > 0) {
                    eachLen = spaceLen / (i - last - 1)
                }

                val sb = StringBuilder()

                for (k in last until i - 1) {
                    sb.append(words[k])

                    var ce = 0
                    while (ce < eachLen) {
                        sb.append(" ")
                        ce++
                    }

                }

                sb.append(words[i - 1])

                result.add(sb.toString())

                last = i
                count = words[i].length
            }
        }

        val sb = StringBuilder()

        for (i in last until words.size - 1) {
            count += words[i].length
            sb.append(words[i] + " ")
        }

        sb.append(words[words.size - 1])
        result.add(sb.toString())

        return result
    }


    fun lineWidth(stringLength : Int):Int
    {
        val result :Int

        if(stringLength <= 414) result = 46  // 1/1 ~ 9/9

        else if(stringLength in 415..4356) result = 44  // 10/10  ~  99/99

        else if(stringLength in 4357..41958) result = 42 // 100/100  ~ 999/999

        else if(stringLength in 41959..399960) result = 40 // 10000/1000 ~ 9999/9999

        else result = 36

        return result
    }

    override
    fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    // store the input text in onSaveInstanceState for screen orientation changes
    override
    fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(ON_SAVED_INSTANCE_STATE_VALUE, storeString)
    }


}