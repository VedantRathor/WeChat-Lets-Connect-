package com.example.wechat

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var textInputLayoutNAME : TextInputLayout
    private lateinit var textInputLayoutCODE : TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etEnterName = findViewById<EditText>(R.id.etEnterName)
          textInputLayoutNAME = findViewById(R.id.textinputlayoutNAME)
         textInputLayoutCODE = findViewById(R.id.textinputlayoutCODE)
        etEnterName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                // Animate hint text size when EditText gains focus
                animateHintTextSize(true , textInputLayoutNAME)
            } else {
                // Animate hint text size back to default when EditText loses focus
                animateHintTextSize(false, textInputLayoutNAME)
            }
        }

        val etCode = findViewById<EditText>(R.id.etCode)
            etCode.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    // Animate hint text size when EditText gains focus
                    animateHintTextSize(true , textInputLayoutCODE)
                } else {
                    // Animate hint text size back to default when EditText loses focus
                    animateHintTextSize(false, textInputLayoutCODE)
                }
            }
        val btnChat = findViewById<Button>(R.id.btnEnterRoom)
         btnChat.setOnClickListener {
             it.setBackgroundResource(R.drawable.btn_click_bg)
             val intent = Intent( this , ChatDashboard::class.java )
             intent.putExtra("NAME" , etEnterName.text.toString().trim())
             intent.putExtra("CODE" , etCode.text.toString().trim())
             startActivity(intent)
         }
    }
    private fun animateHintTextSize(enlarge: Boolean , t : TextInputLayout ) {
        val scale = if (enlarge) 0.9f else 0.9f // Scale factor for hint text size
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, scale)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, scale)

        val animator = ObjectAnimator.ofPropertyValuesHolder( t, scaleX, scaleY)
        animator.duration = 300 // Animation duration in milliseconds
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.start()
    }
}