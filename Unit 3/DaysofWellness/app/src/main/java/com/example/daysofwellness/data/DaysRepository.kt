package com.example.daysofwellness.data

import com.example.daysofwellness.R
import com.example.daysofwellness.model.DayTask

/**
 * @author runningpig66
 * @date 2026/1/5 周一
 * @time 4:20
 */
object DaysRepository {
    // 7 条原始数据
    private val initialData = listOf(
        DayTask(0, R.string.day1_title, R.string.day1_desc, R.drawable.image1),
        DayTask(0, R.string.day2_title, R.string.day2_desc, R.drawable.image2),
        DayTask(0, R.string.day3_title, R.string.day3_desc, R.drawable.image3),
        DayTask(0, R.string.day4_title, R.string.day4_desc, R.drawable.image4),
        DayTask(0, R.string.day5_title, R.string.day5_desc, R.drawable.image5),
        DayTask(0, R.string.day6_title, R.string.day6_desc, R.drawable.image6),
        DayTask(0, R.string.day7_title, R.string.day7_desc, R.drawable.image7)
    )

    // 生成 30 天的数据
    val days: List<DayTask> = (1..30).map { index ->
        val sourceIndex = (index - 1) % initialData.size
        val sourceTask = initialData[sourceIndex]
        sourceTask.copy(day = index)
    }
}
