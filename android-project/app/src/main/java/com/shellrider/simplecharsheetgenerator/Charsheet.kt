package com.shellrider.simplecharsheetgenerator

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.ColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginRight
import androidx.core.view.setMargins

class Charsheet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charsheet)
        setTexts()
        setImages()
    }

    private fun setTexts() {
        val characterName = this.intent.getStringExtra("characterName")
        val characterClass = this.intent.getStringExtra("characterClass")

        findViewById<TextView>(R.id.characterName).text = characterName
        findViewById<TextView>(R.id.characterClass).text = characterClass
    }

    private fun setImages() {
        val dpSize = 42
        val scale = resources.displayMetrics.density
        val dpAsPixels = (dpSize * scale + 0.5f).toInt()
        val marginPixels = (2*scale + 0.5f).toInt()

        val bodyValue = this.intent.getIntExtra("bodyAttributeValue",0)
        val mindValue = this.intent.getIntExtra("mindAttributeValue", 0)
        val cunningValue = this.intent.getIntExtra("cunningAttributeValue", 0)

        for(i in 1..bodyValue){
            val image = ImageView(this)
            val layoutParams = LinearLayout.LayoutParams(dpAsPixels,dpAsPixels)
            layoutParams.setMargins(marginPixels,0,marginPixels,0)
            image.layoutParams = layoutParams
            image.setImageResource(R.drawable.abilitypoint)
            image.setColorFilter(ContextCompat.getColor(this, R.color.purple_500), android.graphics.PorterDuff.Mode.MULTIPLY)

            findViewById<LinearLayout>(R.id.bodyAttributeImageDisplay).addView(image)
        }
        for(i in 1..mindValue){
            val image = ImageView(this)
            val layoutParams = LinearLayout.LayoutParams(dpAsPixels,dpAsPixels)
            layoutParams.setMargins(marginPixels,0,marginPixels,0)
            image.layoutParams = layoutParams
            image.setImageResource(R.drawable.abilitypoint)
            image.setColorFilter(ContextCompat.getColor(this, R.color.purple_500), android.graphics.PorterDuff.Mode.MULTIPLY)

            findViewById<LinearLayout>(R.id.mindAttributeImageDisplay).addView(image)
        }
        for(i in 1..cunningValue){
            val image = ImageView(this)
            val layoutParams = LinearLayout.LayoutParams(dpAsPixels,dpAsPixels)
            layoutParams.setMargins(marginPixels,0,marginPixels,0)
            image.layoutParams = layoutParams
            image.setImageResource(R.drawable.abilitypoint)
            image.setColorFilter(ContextCompat.getColor(this, R.color.purple_500), android.graphics.PorterDuff.Mode.MULTIPLY)

            findViewById<LinearLayout>(R.id.cunningAttributeImageDisplay).addView(image)
        }
    }
}