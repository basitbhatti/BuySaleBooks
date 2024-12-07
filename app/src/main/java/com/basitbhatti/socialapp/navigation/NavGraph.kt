import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.basitbhatti.socialapp.navigation.Screen
import com.basitbhatti.socialapp.ui.screens.SignupScreen

@Composable
fun NavGraph(controller: NavHostController) {


    NavHost(navController = controller, startDestination = Screen.SignupScreen.route) {

        composable(route = Screen.SignupScreen.route) {
            SignupScreen()
        }

    }


}