package com.example.unscramble.data

/**
 * @author runningpig66
 * @date 2026/1/13 周二
 * @time 3:56
 */
const val MAX_NO_OF_WORDS = 10
const val SCORE_INCREASE = 20

// List with all the words for the Game
val allWords: Set<String> =
    setOf(
        "at",
        "sea",
        "home",
        "arise",
        "banana",
        "android",
        "birthday",
        "briefcase",
        "motorcycle",
        "cauliflower"
    )

/**
 * Maps words to their lengths. Each word in allWords has a unique length. This is required since
 * the words are randomly picked inside GameViewModel and the selection is unpredictable.
 */
private val wordLengthMap: Map<Int, String> = allWords.associateBy({ it.length }, { it })

internal fun getUnscrambledWord(scrambledWord: String) = wordLengthMap[scrambledWord.length] ?: ""

//fun getUnscrambledWord(scrambledWord: String): String {
//    val tempWord = scrambledWord.toCharArray().sorted()
//    for (word in allWords) {
//        if (word.toCharArray().sorted() == tempWord) {
//            return word
//        }
//    }
//    return ""
//}
