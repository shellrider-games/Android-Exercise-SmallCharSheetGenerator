package com.shellrider.simplecharsheetgenerator

class Character {
    var freePoints: Int = 3
    var bodyAttribute: Int = 1
    var mindAttribute: Int = 1
    var cunningAttribute: Int = 1
    var characterClass: Int = 0

    fun changeAttribute(attribute: CharacterAttribute, amount: Int){
        if(amount <= freePoints) {
            when(attribute) {
                CharacterAttribute.BODY -> {
                    if (this.bodyAttribute + amount >= 1) {
                        this.freePoints -= amount
                        this.bodyAttribute += amount
                    }
                }
                CharacterAttribute.MIND -> {
                    if (this.mindAttribute + amount >= 1) {
                        this.freePoints -= amount
                        this.mindAttribute += amount
                    }
                }
                CharacterAttribute.CUNNING -> {
                    if (this.cunningAttribute + amount >= 1){
                        this.freePoints -= amount
                        this.cunningAttribute += amount
                    }
                }
            }
        }
    }
}

enum class CharacterAttribute {
    BODY, MIND, CUNNING
}