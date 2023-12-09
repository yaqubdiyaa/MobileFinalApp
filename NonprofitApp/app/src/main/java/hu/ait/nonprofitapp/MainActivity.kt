package hu.ait.nonprofitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.ait.nonprofitapp.ui.screen.LikedOrgs
import hu.ait.nonprofitapp.ui.screen.LoginScreen
import androidx.compose.ui.tooling.preview.Preview
import hu.ait.nonprofitapp.ui.screen.MySwipePage
import hu.ait.nonprofitapp.ui.theme.NonprofitAppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NonprofitAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //NavGraph()
                    MySwipePage()
                }

            }
        }
    }
}



@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = "loginscreen"
    ) {
        composable("loginscreen") {
            LikedOrgs()
            //LoginScreen(
              //  onLoginSuccess = {
                   // navController.navigate("likedorgs")
                //}
            //)
        }
    }
}


