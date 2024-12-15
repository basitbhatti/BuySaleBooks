import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.basitbhatti.buysalebooks.navigation.Screen
import com.basitbhatti.buysalebooks.ui.screens.LoginScreen
import com.basitbhatti.buysalebooks.ui.screens.SignupScreen

@Composable
fun NavGraph(controller: NavHostController) {

    NavHost(navController = controller, startDestination = Screen.SignupScreen.route) {

        composable(route = Screen.SignupScreen.route) {
            SignupScreen()
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen()
        }

    }


}