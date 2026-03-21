package com.example.lunchtray.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.lunchtray.R
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.model.OrderUiState

/**
 * @author runningpig66
 * @date 2026/1/23 周五
 * @time 0:06
 */
@Composable
fun CheckoutScreen(
    orderUiState: OrderUiState,
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(R.string.order_summary),
            fontWeight = FontWeight.Bold
        )
        ItemSummary(
            item = orderUiState.entree,
            modifier = Modifier.fillMaxWidth()
        )
        ItemSummary(
            item = orderUiState.sideDish,
            modifier = Modifier.fillMaxWidth()
        )
        ItemSummary(
            item = orderUiState.accompaniment,
            modifier = Modifier.fillMaxWidth()
        )
        HorizontalDivider(
            thickness = dimensionResource(R.dimen.thickness_divider),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
        )
        OrderSubCost(
            resourceId = R.string.subtotal,
            price = orderUiState.itemTotalPrice.formatPrice(),
            modifier = Modifier.align(Alignment.End)
        )
        OrderSubCost(
            resourceId = R.string.tax,
            price = orderUiState.orderTax.formatPrice(),
            modifier = Modifier.align(Alignment.End)
        )
        Text(
            text = stringResource(
                R.string.total,
                orderUiState.orderTotalPrice.formatPrice()
            ),
            modifier = Modifier.align(Alignment.End),
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            OutlinedButton(
                onClick = onCancelButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.cancel).uppercase())
            }
            Button(
                onClick = onNextButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.submit).uppercase())
            }
        }
    }
}

@Composable
fun ItemSummary(
    item: MenuItem?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item?.name ?: "")
        Text(text = item?.getFormattedPrice() ?: "")
    }
}

@Composable
fun OrderSubCost(
    @StringRes resourceId: Int,
    price: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(resourceId, price),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun CheckoutPreview() {
    CheckoutScreen(
        orderUiState = OrderUiState(
            entree = DataSource.entreeMenuItems[0],
            sideDish = DataSource.sideDishMenuItems[0],
            accompaniment = DataSource.accompanimentMenuItems[0],
            itemTotalPrice = 15.00,
            orderTax = 1.00,
            orderTotalPrice = 16.00
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState()),
        onNextButtonClicked = {},
        onCancelButtonClicked = {}
    )
}
