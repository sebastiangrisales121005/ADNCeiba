package com.ceiba.adnceiba

import com.ceiba.adnceiba.utils.DisableCharacters
import org.junit.Assert
import org.junit.Test


class DisableCharactersTest {
    @Test
    fun emoji_validateDisable_isCorrect() {
        //Act
        val filter = DisableCharacters.disableEmoji()

        //Assert
        Assert.assertNotNull(filter)
    }

    @Test
    fun character_validateDecimalNumber_isFailure() {
        //Arrange
        val character = 0

        //Act
        val filter = DisableCharacters.validateDecimalNumber(character, "", character)

        //Assert
        Assert.assertNull(filter)

    }
}