package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LemonadeApp() {
    var state by remember { mutableStateOf("tree") }
    var taps by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->

        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
        ) {
            when (state) {
                "tree" -> {
                    LemonadeState(
                        imageId = R.drawable.lemon_tree,
                        textId = R.string.tree,
                        onClick = {
                            state = "squeeze"
                            taps = (1..4).random()
                        }
                    )
                }

                "squeeze" -> {
                    LemonadeState(
                        imageId = R.drawable.lemon_squeeze,
                        textId = R.string.squeeze,
                        onClick = {
                            taps--
                            if (taps == 0) {
                                state = "drink"
                            }
                        }
                    )
                }

                "drink" -> {
                    LemonadeState(
                        imageId = R.drawable.lemon_drink,
                        textId = R.string.drink,
                        onClick = { state = "restart" }
                    )
                }

                "restart" -> {
                    LemonadeState(
                        imageId = R.drawable.lemon_restart,
                        textId = R.string.restart,
                        onClick = { state = "tree" }
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeState(imageId: Int, textId: Int, onClick: () -> Unit) {
        Column (
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onClick) {
                Image(painter = painterResource(id = imageId), contentDescription = "" )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(textId),
                color = MaterialTheme.colorScheme.primary
            )
        }
}
