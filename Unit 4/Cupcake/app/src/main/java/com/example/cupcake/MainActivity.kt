package com.example.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcake.ui.theme.CupcakeTheme
// Unit 4: Cupcake App [git checkout starter]
// https://github.com/google-developer-training/basic-android-kotlin-compose-training-cupcake.git
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CupcakeTheme {
                CupcakeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CupcakeAppPreview() {
    CupcakeTheme {
        CupcakeApp()
    }
}
