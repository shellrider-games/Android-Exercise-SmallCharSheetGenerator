package com.shellrider.simplecharsheetgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.util.Log
import android.widget.*


class MainActivity : AppCompatActivity() {
    private var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        character = Character()
        setUpClassSpinner()
        setUpTextEditListener()
        setUpButtons()
        updateValues()
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

    private fun setUpTextEditListener() {
        val textEdit = findViewById<EditText>(R.id.editTextCharacterName)
        textEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val button = findViewById<Button>(R.id.generateCharsheetButton)
                if (s.toString() != "") {
                    button.isEnabled = true
                    button.isClickable = true
                } else {
                    button.isEnabled = false
                    button.isClickable = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setUpButtons() {
        findViewById<Button>(R.id.bodyMinus).setOnClickListener {
            changeCharacterAttribute(CharacterAttribute.BODY, -1)
        }
        findViewById<Button>(R.id.bodyPlus).setOnClickListener {
            changeCharacterAttribute(CharacterAttribute.BODY, 1)
        }
        findViewById<Button>(R.id.mindMinus).setOnClickListener {
            changeCharacterAttribute(CharacterAttribute.MIND, -1)
        }
        findViewById<Button>(R.id.mindPlus).setOnClickListener {
            changeCharacterAttribute(CharacterAttribute.MIND, 1)
        }
        findViewById<Button>(R.id.cunningMinus).setOnClickListener {
            changeCharacterAttribute(CharacterAttribute.CUNNING, -1)
        }
        findViewById<Button>(R.id.cunningPlus).setOnClickListener {
            changeCharacterAttribute(CharacterAttribute.CUNNING, 1)
        }
    }

    private fun updateValues() {
        if(character == null) { return }
        findViewById<TextView>(R.id.attributePointCounter).text = this.character!!.freePoints.toString()
        findViewById<TextView>(R.id.bodyAttributeValue).text = this.character!!.bodyAttribute.toString()
        findViewById<TextView>(R.id.mindAttributeValue).text = this.character!!.mindAttribute.toString()
        findViewById<TextView>(R.id.cunningAttributeValue).text = this.character!!.cunningAttribute.toString()
        findViewById<Spinner>(R.id.classSelectSpinner).setSelection(this.character!!.characterClass)
    }

    private fun changeCharacterAttribute(attribute: CharacterAttribute, amount: Int){
        this.character!!.changeAttribute(attribute, amount)
        this.updateValues()
    }

    fun generateCharsheet(view: View){
        val characterName = findViewById<EditText>(R.id.editTextCharacterName).text.toString()
        val selectedCharacterClass = findViewById<Spinner>(R.id.classSelectSpinner).selectedItemId.toInt()
        val selectedClassName = resources.getStringArray(R.array.characterClasses)[selectedCharacterClass]
        Log.d("GenerateCharsheet", "$characterName - $selectedClassName")

        val intent = Intent(this, Charsheet().javaClass)
        intent.putExtra("characterName", characterName)
        intent.putExtra("characterClass", selectedClassName)
        intent.putExtra("bodyAttributeValue", this.character!!.bodyAttribute)
        intent.putExtra("mindAttributeValue", this.character!!.mindAttribute)
        intent.putExtra("cunningAttributeValue", this.character!!.cunningAttribute)

        startActivity(intent)
    }
}