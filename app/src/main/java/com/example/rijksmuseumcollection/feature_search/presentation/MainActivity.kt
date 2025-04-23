package com.example.rijksmuseumcollection.feature_search.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.rijksmuseumcollection.feature_search.presentation.art_detail.DetailScreen
import com.example.rijksmuseumcollection.feature_search.presentation.search.SearchScreen
import com.example.rijksmuseumcollection.feature_search.presentation.util.Screen
import com.example.rijksmuseumcollection.feature_search.presentation.ui.theme.RijksMuseumCollectionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RijksMuseumCollectionTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Search) {
                    composable<Screen.Search> {
                        SearchScreen(
                            onNavigateToDetail = { objectNumber ->
                                navController.navigate(Screen.Detail(objectNumber))
                            }
                        )
                    }
                    composable<Screen.Detail> {
                        val args = it.toRoute<Screen.Detail>()
                        DetailScreen(
                            objectNumber = args.objectNumber,
                            onBack = {
                                navController.popBackStack(Screen.Search, inclusive = false)
                            }
                        )
                    }
                }
            }
        }
    }
}