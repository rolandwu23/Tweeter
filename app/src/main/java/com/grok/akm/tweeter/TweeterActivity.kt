package com.grok.akm.tweeter

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.grok.akm.tweeter.ConstantCommons.ALL_SPACE_ERROR_MSG
import com.grok.akm.tweeter.ConstantCommons.CASE_ERROR_MSG
import com.grok.akm.tweeter.ConstantCommons.INTEXT_XTRAS_TWEETERS
import com.grok.akm.tweeter.ConstantCommons.ZERO_LENGTH_ERROR_MSG
import kotlinx.android.synthetic.main.activity_tweeter.*

class TweeterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweeter)

        activity_twitsplit_constraintLayout.setOnFocusChangeListener{ _, hasFocus ->
            if (hasFocus) {
                dismissKeyboard(activity_twitsplit_input_TextInputEditText)
            }
        }

        activity_twitsplit_input_TextInputEditText.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                activity_twitsplit_input_TextInputLayout.error = null
            }
        }


        activity_twitsplit_send_button.setOnClickListener{

            if(activity_twitsplit_input_TextInputEditText.hasFocus())
            {
                dismissKeyboard(activity_twitsplit_input_TextInputEditText)
            }
            val input_text = activity_twitsplit_input_TextInputEditText.text.toString()
            val result = validateString(input_text)
            when(result)
            {
                -1 -> activity_twitsplit_input_TextInputLayout.error = ZERO_LENGTH_ERROR_MSG
                -2 -> activity_twitsplit_input_TextInputLayout.error = ALL_SPACE_ERROR_MSG
                -3 -> activity_twitsplit_input_TextInputLayout.error = CASE_ERROR_MSG
                1 ->
                {
                    activity_twitsplit_input_TextInputLayout.error = null
                    val intent = Intent(this@TweeterActivity, AnswerActivity::class.java)
                    intent.putExtra(INTEXT_XTRAS_TWEETERS, input_text)
                    startActivity(intent)
                }
            }
        }

    }

    fun dismissKeyboard(editText: EditText)
    {
        editText.clearFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    // validating input text before navigating to next activity
    fun validateString(st : String):Int
    {
        val result :Int
        val words = st.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if(st.isEmpty())
        {
            result = -1
        }else if(st.trim().isEmpty())
        {
            result = -2
        }

        else if(st.length > 50 && words.size == 1)
        {
            result = -3
        } else
        {
            result = 1
        }

        return result
    }


}

