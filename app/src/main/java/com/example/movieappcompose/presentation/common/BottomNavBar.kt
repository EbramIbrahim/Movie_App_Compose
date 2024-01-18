package com.example.movieappcompose.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieappcompose.domain.model.BottomNavItems

@Composable
fun BottomNavBar(
    bottomItems: List<BottomNavItems>,
    onItemClick:(String) -> Unit,
    navController: NavController
) {


    val entry = navController.currentBackStackEntryAsState()
    
    
    NavigationBar {
        Row(modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)) {
            bottomItems.forEach { bottomNavItems ->

                val selected = bottomNavItems.route == entry.value?.destination?.route

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        onItemClick(bottomNavItems.route)
                        navController.popBackStack()
                    },
                    icon = {
                        Icon(
                            imageVector = bottomNavItems.icon,
                            contentDescription = bottomNavItems.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(
                            text = bottomNavItems.title,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },

                )
            }
        }
    }

}












