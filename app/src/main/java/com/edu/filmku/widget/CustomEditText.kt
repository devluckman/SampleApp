package com.edu.filmku.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.edu.filmku.R
import com.edu.filmku.R.styleable.TextInputCustom
import com.edu.filmku.R.styleable.TextInputCustom_hint_text
import com.edu.filmku.R.styleable.TextInputCustom_input_type
import com.edu.filmku.R.styleable.TextInputCustom_title
import com.edu.filmku.core.findIdByLazy
import com.google.android.material.textfield.TextInputLayout

/**
 *
 * Created by Lukmanul Hakim on  16/02/24
 * devs.lukman@gmail.com
 */
class CustomEditText(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    private val titleText: TextView by findIdByLazy(R.id.tv_title)
    private val textInputLayout: TextInputLayout by findIdByLazy(R.id.text_input_layout)
    private val mEditText: EditText by findIdByLazy(R.id.edit_text)
    val editText : EditText get() = mEditText

    init {
        inflate(context, R.layout.custom_edit_text, this)
        context.obtainStyledAttributes(
            attributeSet, TextInputCustom, 0, 0
        ).apply {
            val title = getString(TextInputCustom_title).orEmpty()
            val hintText = getString(TextInputCustom_hint_text).orEmpty()
            val inputTypeRes = getInt(TextInputCustom_input_type, 0)
            titleText.text = title
            mEditText.hint = hintText
            mEditText.inputType = when(inputTypeRes) {
                0 -> android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                1 -> {
                    setTogglePassword()
                    android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                else -> android.text.InputType.TYPE_CLASS_TEXT
            }
            mEditText.addTextChangedListener {
                textInputLayout.error = null
                textInputLayout.isErrorEnabled = false
                textInputLayout.setPadding(0, 0, 0, 0)
            }
        }.recycle()
    }

    private fun setTogglePassword() {
        textInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
    }

    fun setError(message : String) {
        textInputLayout.error = message
    }
}