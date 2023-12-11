package hu.ait.nonprofitapp.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


//update state in state machine in the view model
//states are helpful so that we can show progress bar yk

class LoginViewModel:ViewModel() {

    var loginUIState: LoginUiState by mutableStateOf(LoginUiState.Init) // so it will start in this init state
    //this is what will monitor on the composable, if there is a change in this state, we must recompose

    /*
    //these are just things needed for firebase authentication
    private lateinit var auth: FirebaseAuth

    init {
        auth = Firebase.auth
    }

    //two functions that will be called from the screen when we do smth
    //function for registering user, and one for logging in

    fun registerUser(email: String, password: String) {
        loginUIState =
            LoginUiState.Loading //when we press register button, suddenly jump to loading state

        try {
            auth.createUserWithEmailAndPassword(email, password) //firebase has this function!
                .addOnSuccessListener {
                    loginUIState = LoginUiState.RegisterSuccess
                }
                .addOnFailureListener {//this error is what happens on firebase side, like pass too short, email already used, etc.
                    loginUIState = LoginUiState.Error(it.message)
                }
        } catch (e: Exception) { //this is exception when smth is wrong w mobile phone (like wifi not available)
            loginUIState = LoginUiState.Error(e.message)
        }
    }

    /*
    fun loginUser(email: String, password: String) {
        loginUIState = LoginUiState.Loading //when we press register button, suddenly jump to loading state

        try {
            auth.signInWithEmailAndPassword(email, password) //firebase has this function!
                .addOnSuccessListener {
                    loginUIState = LoginUiState.LoginSuccess
                }
                .addOnFailureListener {//this error is what happens on firebase side, like pass too short, email already used, etc.
                    loginUIState = LoginUiState.Error(it.message)
                }
        } catch (e: Exception) { //this is exception when smth is wrong w mobile phone (like wifi not available)
            loginUIState = LoginUiState.Error(e.message)
        }
    }

}
 */


    suspend fun loginUser(email: String, password: String): AuthResult? {
        loginUIState = LoginUiState.Loading

        return try {
            //so we will wait for response from firebase
            //if exception not thrown, then jumt to success state
            val result = auth.signInWithEmailAndPassword(email, password).await()


            loginUIState = LoginUiState.LoginSuccess

            //but we will return result object
            //the auth result is the result returned
            result
        } catch (e: java.lang.Exception) {
            loginUIState = LoginUiState.Error(e.message)

            null
        }
    }

     */

    suspend fun loginUser(email: String, password: String) {}


    fun registerUser(email: String, password: String) {}

    //list possible states
    //create UI state interface, and every instance is listed here (which is why we say sealed
    sealed interface LoginUiState {
        object Init : LoginUiState //default state

        // all of these below are extended from loginui state yk
        object Loading : LoginUiState
        object LoginSuccess : LoginUiState
        object RegisterSuccess : LoginUiState
        data class Error(val error: String?) : LoginUiState
    }
}


