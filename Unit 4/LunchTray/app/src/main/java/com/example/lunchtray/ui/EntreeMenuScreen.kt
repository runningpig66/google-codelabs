package com.example.lunchtray.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lunchtray.R
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.MenuItem.EntreeItem

/**
 * @author runningpig66
 * @date 2026/1/23 周五
 * @time 3:11
 */
@Composable
fun EntreeMenuScreen(
    options: List<EntreeItem>,
    modifier: Modifier = Modifier,
    selectedItemName: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (EntreeItem) -> Unit
) {
    BaseMenuScreen(
        options = options,
        modifier = modifier,
        selectedItemName = selectedItemName,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onSelectionChanged = onSelectionChanged
    )
}

@Preview(showBackground = true)
@Composable
fun EntreeMenuPreview() {
    EntreeMenuScreen(
        options = DataSource.entreeMenuItems,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState()),
        selectedItemName = "",
        onCancelButtonClicked = {},
        onNextButtonClicked = {},
        onSelectionChanged = {}
    )
}
