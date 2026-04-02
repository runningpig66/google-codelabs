package com.example.racetracker

import com.example.racetracker.ui.RaceParticipant
import com.example.racetracker.ui.utils.TestLogger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author runningpig66
 * @date 2026-04-02
 * @time 6:45
 */
@OptIn(ExperimentalCoroutinesApi::class)
class RaceParticipantTest {
    private val raceParticipant = RaceParticipant(
        name = "Test",
        maxProgress = 100,
        progressDelayMillis = 500L,
        initialProgress = 0,
        progressIncrement = 1,
        logger = TestLogger()
    )

    @Test
    fun raceParticipant_RaceStarted_ProgressUpdated() = runTest {
        val expectedProgress = 1
        // 如果不使用 launch 而是直接调用 run()，测试调度器会触发自动快进机制，瞬间跳过所有 delay() 并在当前帧执行完毕，导致无法捕获和测试进度的中间状态。
        // 通过使用 launch，我们将协程任务放入了虚拟调度器的就绪队列中暂缓执行。这允许测试代码充当虚拟时钟的控制者：
        // 使用 advanceTimeBy() 手动推进虚拟时间，并配合 runCurrent() 强制执行到达该时间点的待处理任务，从而实现对协程中间状态的精准“步进式”测试。
        launch { raceParticipant.run() }
        // 注意：你可以在 runtTest 构建器中直接调用 raceParticipant.run() ，但默认的测试实现会忽略对 delay() 的调用。因此， run() 在你能够分析进度之前就已经完成执行。
        // raceParticipant.progressDelayMillis 属性的值决定了赛程进度更新的时间间隔。为了在经过 progressDelayMillis 时间后测试进度，你需要在测试中加入某种形式的延迟。
        // 使用 advanceTimeBy() 辅助函数将时间前进 raceParticipant.progressDelayMillis 的数值。 advanceTimeBy() 函数有助于缩短测试执行时间。
        advanceTimeBy(raceParticipant.progressDelayMillis)
        // 由于 advanceTimeBy() 未在指定持续时间执行计划的任务，你需要调用 runCurrent() 函数。该函数会在当前时间执行任何未完成的待处理任务。
        runCurrent()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    @Test
    fun raceParticipant_RaceFinished_ProgressUpdated() = runTest {
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.maxProgress * raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(100, raceParticipant.currentProgress)
    }

    @Test
    fun raceParticipant_RacePaused_ProgressUpdated() = runTest {
        val expectedProgress = 5
        val racerJob = launch { raceParticipant.run() }
        advanceTimeBy(expectedProgress * raceParticipant.progressDelayMillis)
        runCurrent()
        racerJob.cancelAndJoin()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    @Test
    fun raceParticipant_RacePausedAndResumed_ProgressUpdated() = runTest {
        val expectedProgress = 5
        repeat(2) {
            val racerJob = launch { raceParticipant.run() }
            advanceTimeBy(expectedProgress * raceParticipant.progressDelayMillis)
            runCurrent()
            // 模拟真实业务场景中的“暂停”操作：在 UI 层，状态改变会导致 LaunchedEffect 被移出组合，从而自动取消绑定的协程。
            // 在测试环境下，必须显式调用 cancelAndJoin() 来销毁当前循环的协程。若不主动取消，
            // 旧协程与下一轮创建的新协程将在虚拟时间轴上交替执行，导致 currentProgress 被并发修改，从而违背“暂停与恢复”的测试语义。
            racerJob.cancelAndJoin()
        }
        assertEquals(expectedProgress * 2, raceParticipant.currentProgress)
    }

    @Test(expected = IllegalArgumentException::class)
    fun raceParticipant_ProgressIncrementZero_ExceptionThrown() = runTest {
        RaceParticipant(name = "Progress Test", progressIncrement = 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun raceParticipant_MaxProgressZero_ExceptionThrown() {
        RaceParticipant(name = "Progress Test", maxProgress = 0)
    }
}
