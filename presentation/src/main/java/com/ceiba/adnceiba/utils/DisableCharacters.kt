package com.ceiba.adnceiba.utils

import android.text.InputFilter

class DisableCharacters {
    companion object {
        fun disableEmoji(): InputFilter {
            val emojiFilter = InputFilter { source, start, end, _, _, _ ->
                for (index in start until end) {
                    when (Character.getType(source[index])) {
                        '*'.code,
                        Character.OTHER_SYMBOL.toInt(),
                        Character.SURROGATE.toInt() -> {
                            return@InputFilter ""
                        }
                        Character.LOWERCASE_LETTER.toInt() -> {
                            return@InputFilter validateCharacterLowerCase(index, source, end)
                        }
                        Character.DECIMAL_DIGIT_NUMBER.toInt() -> {
                            return@InputFilter validateDecimalNumber(index, source, end)
                        }
                        Character.OTHER_PUNCTUATION.toInt() -> {
                            return@InputFilter validateOtherPunctuation(index, source, end)
                        }
                        Character.MATH_SYMBOL.toInt() -> {
                            return@InputFilter validateMathSymbol(index, source, end)
                        }
                    }
                }
                return@InputFilter null
            }

            return emojiFilter
        }

        fun validateCharacterLowerCase(index: Int, source: CharSequence, end: Int): String? {
            val index2 = index + 1
            if (index2 < end && Character.getType(source[index + 1]) == Character.NON_SPACING_MARK.toInt()) {
                return ""
            }
            return null
        }

        fun validateDecimalNumber(index: Int, source: CharSequence, end: Int): String? {
            val index2 = index + 1
            val index3 = index + 2
            if (index2 < end && index3 < end &&
                Character.getType(source[index2]) == Character.NON_SPACING_MARK.toInt() &&
                Character.getType(source[index3]) == Character.ENCLOSING_MARK.toInt()) {
                return ""
            }
            return null
        }

        fun validateOtherPunctuation(index: Int, source: CharSequence, end: Int): String? {
            val index2 = index + 1

            if (index2 < end && Character.getType(source[index2]) == Character.NON_SPACING_MARK.toInt()) {
                return ""
            }
            return null
        }

        fun validateMathSymbol(index: Int, source: CharSequence, end: Int): String? {
            val index2 = index + 1
            if (index2 < end && Character.getType(source[index2]) == Character.NON_SPACING_MARK.toInt()) {
                return ""
            }

            return null
        }

    }
}