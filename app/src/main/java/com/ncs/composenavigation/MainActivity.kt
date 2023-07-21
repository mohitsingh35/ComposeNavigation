package com.ncs.composenavigation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ncs.composenavigation.destinations.PostScreenDestination
import com.ncs.composenavigation.destinations.ProfileScreenDestination
import com.ncs.composenavigation.ui.theme.ComposeNavigationTheme
import com.ncs.composenavigation.ui.theme.User
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
            navigator.navigate(
                ProfileScreenDestination( //this is Destination class generated on own
                    User(
                        name = "Mohit Singh",
                        id = "mohit.singh",
                        created = LocalDateTime.now()
                    )
                )
            )
        }) {
            Text("Go to Profile Screen")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    user: User
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            Column() {
                Text("Profile Screen: ${user.name} ${user.id} ${user.created}", textAlign = TextAlign.Center)
                Text("${user.name}", textAlign = TextAlign.Center)
                Text("${user.id}", textAlign = TextAlign.Center)
                Text("${user.created}", textAlign = TextAlign.Center)
            }
        

        Button(onClick = {
            navigator.navigate(PostScreenDestination())
        }) {
            Text("Go to Post Screen")
        }
    }
}
@Destination
@Composable
fun PostScreen(
    showOnlyPostsByUser: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostsByUser")
    }
}
