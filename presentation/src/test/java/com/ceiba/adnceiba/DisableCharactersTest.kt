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
}