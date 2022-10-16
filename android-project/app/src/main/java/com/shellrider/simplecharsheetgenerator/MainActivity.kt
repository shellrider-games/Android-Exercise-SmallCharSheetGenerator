package com.shellrider.simplecharsheetgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.util.Log
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpClassSpinner()
    }

    private fun setUpClassSpinner() {
        val characterClasses = resources.getStringArray(R.array.characterClasses)
        val spinner = findViewById<Spinner>(R.id.classSelectSpinner)

        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                R.layout.spinner_list, characterClasses)
            adapter.setDropDownViewResource(R.layout.spinner_list)
            spinner.adapter = adapter
        }
    }

    fun generateCharsheet(view: View){
        val characterName = findViewById<EditText>(R.id.editTextCharacterName).text.toString()
        val selectedCharacterClass = findViewById<Spinner>(R.id.classSelectSpinner).selectedItemId.toInt()
        val selectedClassName = resources.getStringArray(R.array.characterClasses)[selectedCharacterClass]
        Log.d("GenerateCharsheet", "$characterName - $selectedClassName");
    }
}