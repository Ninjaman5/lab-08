package com.example.lab_8


import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WizardTest {
    private lateinit var evilWizard: Wizard

    // Set up a new wizard before every single test
    @Before
    fun setUp() {
        evilWizard = Wizard("Evil Wizard", 30, 10)
    }

    @Test
    fun castSpell_explosion_successful() {
        val damageDealt = evilWizard.castSpell("Explosion")

        assertEquals(30, damageDealt) // spellPower of 10 * 3 = 30

        assertEquals(20, evilWizard.mana) // mana of 30 - 10 = 20
    }

    @Test
    fun castSpell_explosion_unsuccessful() {
        evilWizard.mana = 5 // Evil Wizard does not have enough mana to cast "Explosion"
        val damageDealt = evilWizard.castSpell("Explosion")

        assertEquals(0, damageDealt)

        assertEquals(5, evilWizard.mana) // mana still is 5
    }

    @Test
    fun restoreMana_amountAddedExceedsOneHundred_manaSetToOneHundred() {
        evilWizard.restoreMana(80) // 30 + 80 = 110, so mana should be set to 100

        assertEquals(100, evilWizard.mana)
    }

    @Test
    fun restoreMana_amountAddedDoesNotExceedOneHundred_manaIncreasesCorrectly() {
        evilWizard.restoreMana(40) // 30 + 40 = 70, so mana should be set to 70

        assertEquals(70, evilWizard.mana)
    }

    @Test
    fun train_amountOfManaIsOverFive_trainCorrectly(){
        evilWizard.mana = 6
        evilWizard.spellPower = 2
        evilWizard.train() // mana over 5, should succeed

        assertEquals(1, evilWizard.mana) // 6 - 5 = 1
        assertEquals(3, evilWizard.spellPower) // 2 + 1 = 3
    }

    @Test
    fun train_amountOfManaIsNotOverFive_trainIncorrectly(){
        evilWizard.mana = 3
        evilWizard.spellPower = 2
        evilWizard.train() // mana not over 5, should fail, no update

        assertEquals(3, evilWizard.mana) // no change, still 3
        assertEquals(2, evilWizard.spellPower) // no change, still 2
    }


}