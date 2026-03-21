package com.example.tiptime

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 2:31
 */

class TipUITests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TipTimeLayout()
                }
            }
        }

        // UI 组件可以通过 composeTestRule 作为节点进行访问。
        // 常见的做法是使用 onNodeWithText() 方法访问包含特定文本的节点。
        composeTestRule.onNodeWithText("Bill Amount")
            // 接下来你可以调用 performTextInput() 方法并传入想要输入到 TextField 可组合项中的文本。
            .performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("20")
        // 在使用 Compose 进行仪器测试时，可以直接在 UI 组件上调用断言。
        // 虽然存在多种断言方法，但此场景下您需要使用 assertExists() 方法。
        // 显示小费金额的 Text 可组合项预期应展示： Tip Amount: $2.00 。
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with this text was found.")
    }
}
