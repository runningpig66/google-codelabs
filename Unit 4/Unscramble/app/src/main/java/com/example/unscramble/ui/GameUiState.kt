package com.example.unscramble.ui

/**
 * @author runningpig66
 * @date 2026/1/10 周六
 * @time 2:00
 * Data class that represents the game UI state
 */
data class GameUiState(
    val currentScrambledWord: String = "",
    val currentWordCount: Int = 1,
    val score: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false
)
