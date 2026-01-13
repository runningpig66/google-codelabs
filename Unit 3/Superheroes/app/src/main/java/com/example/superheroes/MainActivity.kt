package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.superheroes.model.HeroRow
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme

// Unit 3: Superheroes App [git checkout main]
// https://github.com/google-developer-training/basic-android-kotlin-compose-training-superheroes.git
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesTheme {
                SuperheroesApp()
            }
        }
    }
}

/**
 * Composable that displays an app bar and a list of heroes.
 */
@Composable
fun SuperheroesApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar() }
    ) { innerPadding -> // 注意：这个 PaddingValues 是 Scaffold 计算好的，包含了 topBar，建议在内部使用
        /* Important: It is not a good practice to access data source directly from the UI.
        In later units you will learn how to use ViewModel in such scenarios that takes the
        data source as a dependency and exposes heroes.
        */
        val heroRows: List<HeroRow> = remember { HeroesRepository.heroesAsRows(copies = 3) }
        HeroesList(
            heroRows = heroRows,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

/**
 * Composable that displays a Top Bar with an icon and text.
 *
 * @param modifier modifiers to set to this composable
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
//            scrolledContainerColor = MaterialTheme.colorScheme.surface,
//            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
//            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
//            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun SuperHeroesPreview() {
    SuperheroesTheme {
        SuperheroesApp()
    }
}
