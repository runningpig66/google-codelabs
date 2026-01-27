package com.example.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lunchtray.R
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.MenuItem

/**
 * @author runningpig66
 * @date 2026/1/23 周五
 * @time 1:12
 */
@Composable
fun <T : MenuItem> BaseMenuScreen(
    options: List<T>,
    modifier: Modifier = Modifier,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    onSelectionChanged: (T) -> Unit
) {
    // TODO 建议修改 BaseMenuScreen，删除内部的 selectedItemName，直接让外部传进来！
    // 当前使用内部 rememberSaveable 管理状态会导致“前进丢失”Bug。现象：
    // 1. 当用户点击 Back 键时，当前页面从导航栈中 出栈 (Pop) 并被彻底销毁，其内部维持的 rememberSaveable 状态也随之消失。
    // 2. 当用户再次点击 Next 重新进入该页面时，Navigation 会创建一个 全新的页面实例。
    // 3. 由于新页面初始化时只使用默认值（空），且未读取 ViewModel 中的历史数据，导致用户之前选中的数据在 UI 上显示为空（看起来像丢了）。
    // 解决方案：必须进行状态提升 (State Hoisting)，删除内部状态，强制 UI 直接从 ViewModel 读取数据。
    var selectedItemName by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        options.forEach { item ->
            val onClick = {
                selectedItemName = item.name
                onSelectionChanged(item)
            }
            MenuItemRow(
                item = item,
                selectedItemName = selectedItemName,
                onClick = onClick,
                modifier = Modifier
                    .selectable(
                        selected = selectedItemName == item.name,
                        onClick = onClick
                    )
                    .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            )
        }
        MenuScreenButtonGroup(
            selectedItemName = selectedItemName,
            onCancelButtonClicked = onCancelButtonClicked,
            onNextButtonClicked = {
                // Assert not null bc next button is not enabled unless selectedItem is not null.
                onNextButtonClicked()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Composable
fun MenuItemRow(
    item: MenuItem,
    selectedItemName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedItemName == item.name,
            onClick = onClick
        )
        Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = item.getFormattedPrice(),
                style = MaterialTheme.typography.bodyMedium
            )
            HorizontalDivider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Composable
fun MenuScreenButtonGroup(
    selectedItemName: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        OutlinedButton(
            onClick = onCancelButtonClicked,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.cancel).uppercase(),
            )
        }
        Button(
            onClick = onNextButtonClicked,
            modifier = Modifier.weight(1f),
            enabled = selectedItemName.isNotEmpty()
        ) {
            Text(
                text = stringResource(R.string.next).uppercase()
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BaseMenuPreview() {
    BaseMenuScreen(
        options = DataSource.entreeMenuItems,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        onCancelButtonClicked = {},
        onNextButtonClicked = {},
        onSelectionChanged = {}
    )
}
