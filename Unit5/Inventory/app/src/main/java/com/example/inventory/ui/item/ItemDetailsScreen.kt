package com.example.inventory.ui.item

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme
import com.example.inventory.ui.utils.PhonePreviews
import kotlinx.coroutines.launch

/**
 * @author runningpig66
 * @date 2026-04-14
 * @time 11:01
 */
object ItemDetailsDestination : NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.item_detail_title
    const val itemIdArg = "itemId"

    // 构建带有动态参数的路由模板 (Route Template):
    // 在 Navigation 中，当目的地需要接收参数时，需使用带花括号 {} 的字符串模板来声明参数占位符 (Placeholder)。
    // 格式解析： "base_route/{argument_name}" 对应本页面的 "item_details/{itemId}"
    // - "item_details" : 静态的基础路由路径，用于标识目标页面。
    // - "/{itemId}" : 动态参数声明（占位符）。大括号通知 NavController 在匹配到该路由时，将斜杠后的具体值（如 "5"）截取下来，
    // 并将其与大括号内的变量名绑定，生成键值对 Key: "itemId" -> Value: "5"。(为避免硬编码拼写错误，"itemId" 这个键名定义为常量 itemIdArg)
    // 导航工作流示例：
    // 1. 触发导航：调用 navController.navigate("item_details/5")。
    // 2. 路由匹配：系统将目标路由 "item_details/5" 与当前模板 "item_details/{itemId}" 进行模式匹配。
    // 3. 参数提取：匹配成功后，系统提取值 "5"，并生成键值对 (Key: "itemId", Value: "5")。
    // 4. 类型转换：在目标的 composable 定义中，通过 arguments 参数配置将提取到的字符串值转换为指定的类型（如 NavType.IntType）。
    // 5. 状态传递：转换后的参数会被存储在 [SavedStateHandle] 中，供目标页面的 ViewModel 或 UI 使用。
    val routeWithArgs = "$route/{$itemIdArg}"
}

@Composable
fun ItemDetailsScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val itemDetailsUiState: ItemDetailsUiState by viewModel.itemDetailsUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    ItemDetailsScreenContent(
        itemDetailsUiState = itemDetailsUiState,
        navigateToEditItem = navigateToEditItem,
        navigateBack = navigateBack,
        onSellItem = viewModel::reduceQuantityByOne,
        onDelete = {
            // 在 UI 层使用 rememberCoroutineScope 启动协程，可以确保异步操作与当前组件的生命周期绑定。
            // 若在删除期间该节点从视图树中移除，协程会自动取消以避免内存泄漏。
            // 调用 deleteItem() 时，当前协程会被挂起，主线程随即被释放去处理其他绘制事件，不会发生阻塞。
            // 等待后台的数据库物理删除彻底完成后，协程恢复执行，随后触发 navigateBack() 回退页面。
            // 将耗时操作交由 ViewModel 执行，并将页面路由控制权保留在 UI 层，既保证了严格的时序，又符合 MVVM 的解耦规范。
            coroutineScope.launch {
                viewModel.deleteItem()
                navigateBack()
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemDetailsScreenContent(
    itemDetailsUiState: ItemDetailsUiState,
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    onSellItem: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(ItemDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditItem(itemDetailsUiState.itemDetails.id) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_item_title)
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        ItemDetailsBody(
            itemDetailsUiState = itemDetailsUiState,
            onSellItem = onSellItem,
            onDelete = onDelete,
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun ItemDetailsBody(
    itemDetailsUiState: ItemDetailsUiState,
    onSellItem: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
        ItemDetails(
            item = itemDetailsUiState.itemDetails.toItem(),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSellItem,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            enabled = !itemDetailsUiState.outOfStock
        ) {
            Text(text = stringResource(R.string.sell))
        }
        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.delete))
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = {
                    deleteConfirmationRequired = false
                },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}

@Composable
fun ItemDetails(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            ItemDetailsRow(
                labelResId = R.string.item,
                itemDetail = item.name,
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
            )
            ItemDetailsRow(
                labelResId = R.string.quantity_in_stock,
                itemDetail = item.quantity.toString(),
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
            )
            ItemDetailsRow(
                labelResId = R.string.price,
                itemDetail = item.formattedPrice(),
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Composable
private fun ItemDetailsRow(
    @StringRes labelResId: Int,
    itemDetail: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResId))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = itemDetail,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = { Text(text = stringResource(R.string.attention)) },
        text = { Text(text = stringResource(R.string.delete_question)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(R.string.yes))
            }
        }
    )
}

@PhonePreviews
@Composable
fun ItemDetailsBodyPreview() {
    InventoryTheme {
        ItemDetailsScreenContent(
            itemDetailsUiState = ItemDetailsUiState(
                outOfStock = true,
                itemDetails = ItemDetails(1, "MacBook Pro", "15000", "5")
            ),
            navigateToEditItem = {},
            onSellItem = {},
            navigateBack = {},
            onDelete = {}
        )
    }
}
