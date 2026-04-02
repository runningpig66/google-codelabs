@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.marsphotos.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marsphotos.R
import com.example.marsphotos.ui.screens.HomeScreen
import com.example.marsphotos.ui.screens.MarsViewModel
import com.example.marsphotos.ui.theme.MarsPhotosTheme
import com.example.marsphotos.ui.utils.PhonePreviews

/**
 * @author runningpig66
 * @date 2026-04-02
 * @time 23:47
 */
@Composable
fun MarsPhotosApp() {
    // 1. 【状态中转枢纽】实例化 TopAppBar 的滚动行为控制器 (ScrollBehavior)。enterAlways 策略定义了：
    // 无论内部列表滚动到什么位置，只要用户产生向下（下拉）的滑动趋势，标题栏就会立即出现。它在内存中维护着当前滚动的偏移量 (Offset) 状态。
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        // 2. 【数据生产者】建立嵌套滚动事件的拦截与通信桥梁。将控制器的 Connection 绑定到父容器 Scaffold 上。它的作用是：
        // 作为一个监听器，拦截其内部可滚动子组件（如 LazyVerticalGrid）产生的所有物理滑动偏移量，并实时上报给 scrollBehavior。
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        // 3. 【数据消费者】驱动 UI 发生形变。将装满滚动偏移量数据的 scrollBehavior 注入给 TopAppBar。
        // TopAppBar 会监听这些数据的变化，动态计算自身的位移 (TranslationY) 或高度，从而实现自动折叠/展开的丝滑视差动画。
        topBar = { MarsTopAppBar(scrollBehavior = scrollBehavior) }
    ) { innerPadding ->
        val marsViewModel: MarsViewModel = viewModel()
        HomeScreen(
            marsUiState = marsViewModel.marsUiState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
fun MarsTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier
    )
}

@PhonePreviews
@Composable
fun ResultScreenPreview() {
    MarsPhotosTheme {
        MarsPhotosApp()
    }
}
