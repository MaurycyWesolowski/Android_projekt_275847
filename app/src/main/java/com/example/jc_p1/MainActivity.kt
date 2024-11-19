package com.example.jc_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.jc_p1.ui.theme.JC_P1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC_P1Theme {
                aplikacja()
            }
        }
    }
}

@Composable
fun aplikacja() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            startscreen(onNavigate = { navController.navigate("screen2")
            })
        }
        composable("screen2") {
            edycjatekstu(onNextClick = { inputText -> navController.navigate("display/$inputText"){
            }
                })
        }
        composable(
            "display/{text}",
            arguments = listOf(navArgument("text") { type = NavType.StringType })
        ) { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text").orEmpty()
            wyswietl(text = text, onBackClick = { navController.popBackStack() })
        }
    }
}

@Composable
fun startscreen(onNavigate: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "witam"
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onNavigate) {
            Text("idz do screen 2")
        }
    }
}

@Composable
fun edycjatekstu(onNextClick: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "wpisz tekst",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text("") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = { onNextClick(text) }) {
            Text("przejdz do screen 3 z danym tekstem")
        }
    }
}
@Composable
fun wyswietl(text: String, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "tekst:",
            )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onBackClick) {
            Text("powrot do screen 2")
        }
    }
}
