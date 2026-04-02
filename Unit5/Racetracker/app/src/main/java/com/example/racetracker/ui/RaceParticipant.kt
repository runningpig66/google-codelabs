package com.example.racetracker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.racetracker.ui.utils.AndroidLogger
import com.example.racetracker.ui.utils.Logger
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay

/**
 * @author runningpig66
 * @date 2026-04-01
 * @time 22:52
 * This class represents a state holder for race participant.
 */
class RaceParticipant(
    val name: String, // 参赛者名称
    val maxProgress: Int = 100, // 最大进度值（进度条满值）
    val progressDelayMillis: Long = 100L, // 每次进度增加的延迟时间（毫秒）
    private val progressIncrement: Int = 1, // 每次增加的进度值
    private val initialProgress: Int = 0, // 初始进度值
    private val logger: Logger = AndroidLogger()
) {
    init {
        require(maxProgress > 0) {
            "maxProgress=$maxProgress; must be > 0"
        }
        require(progressIncrement > 0) {
            "progressIncrement=$progressIncrement; must be > 0"
        }
    }

    /**
     * Indicates the race participant's current progress 当前进度
     */
    var currentProgress by mutableIntStateOf(initialProgress)
        private set

    /**
     * Updates the value of [currentProgress] by value [progressIncrement] until it reaches
     * [maxProgress]. There is a delay of [progressDelayMillis] between each update.
     */
    suspend fun run() {
        try {
            while (currentProgress < maxProgress) {
                delay(progressDelayMillis)
                currentProgress += progressIncrement
            }
        } catch (e: CancellationException) {
            logger.e("RaceParticipant", "$name: ${e.message}")
            throw e // Always re-throw CancellationException.
        }
    }

    /**
     * Regardless of the value of [initialProgress] the reset function will reset the [currentProgress] to 0
     */
    fun reset() {
        currentProgress = 0
    }
}

/**
 * The Linear progress indicator expects progress value in the range of 0-1.
 * This property calculate the progress factor to satisfy the indicator requirements.
 * 进度因子（用于 LinearProgressIndicator，范围 0..1）
 */
val RaceParticipant.progressFactor: Float
    get() = currentProgress / maxProgress.toFloat()
