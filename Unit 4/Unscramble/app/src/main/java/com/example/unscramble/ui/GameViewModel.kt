package com.example.unscramble.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * @author runningpig66
 * @date 2026/1/10 周六
 * @time 1:59
 * ViewModel containing the app data and methods to process the data TODO Hilt
 */
class GameViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    // Game UI state
    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(value = GameUiState())

    // The asStateFlow() makes this mutable state flow a read-only state flow.
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    @OptIn(SavedStateHandleSaveableApi::class)
    var userGuess by savedStateHandle.saveable { mutableStateOf("") }
        private set

    // Set of words used in the game
    private var usedWords: MutableSet<String> = mutableSetOf()

    // The current scrambled word.
    private lateinit var currentWord: String

    init {
        resetGame()
    }

    // Re-initializes the game data to restart the game.
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    // Update the user's guess
    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord.trim()
    }

    // Checks if the user's guess is correct. Increases the score accordingly.
    fun checkUserGuess() {
        Log.d("GameViewModel", "userGuess: $userGuess")
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            // User's guess is correct, increase the score
            // and call updateGameState() to prepare the game for next round
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            // User's guess is wrong, show an error
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        // Reset user guess
        updateUserGuess(guessedWord = "")
    }

    // Skip to next word
    fun skipWord() {
        updateGameState(updatedScore = _uiState.value.score)
        updateUserGuess(guessedWord = "")
    }

    // Picks a new currentWord and currentScrambledWord and updates UiState according to current game state.
    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS) {
            //Last round in the game, update isGameOver to true, don't pick a new word
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else {
            // Normal round in the game
            _uiState.update { currentState ->
                currentState.copy(
                    currentWordCount = currentState.currentWordCount.inc(),
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    isGuessedWordWrong = false,
                    score = updatedScore
                )
            }
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord) == word) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    private fun pickRandomWordAndShuffle(): String {
        // Continue picking up a new random word until you get one that hasn't been used before
        currentWord = allWords.random()
        return if (usedWords.contains(currentWord)) {
            pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            Log.d("GameViewModel", "currentWord: $currentWord")
            shuffleCurrentWord(currentWord)
        }
    }
}
