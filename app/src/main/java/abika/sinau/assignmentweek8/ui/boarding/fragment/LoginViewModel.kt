package abika.sinau.assignmentweek8.ui.boarding.fragment

import abika.sinau.assignmentweek8.repository.users.RepositoryLocalUsers
import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val repository = RepositoryLocalUsers(application.applicationContext)

    var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading
    var _isLogin = MutableLiveData<Int>()
    var isLogin: LiveData<Int> = _isLogin
    var _isSuccess = MutableLiveData<Boolean>()
    var isSuccess: LiveData<Boolean> = _isSuccess
    var _isError = MutableLiveData<String>()
    var isError: LiveData<String> = _isError

    // Error State
    var _emailEmpty = MutableLiveData<Boolean>()
    var emailEmpty: LiveData<Boolean> = _emailEmpty
    var _emailPattern = MutableLiveData<Boolean>()
    var emailPattern: LiveData<Boolean> = _emailPattern
    var _passwordEmpty = MutableLiveData<Boolean>()
    var passwordEmpty: LiveData<Boolean> = _passwordEmpty

    private val TAG = "LoginViewModel"

    fun loginUsers(email: String?, password: String?) {

        when {
            email?.isEmpty()!! -> {
                _emailEmpty.value = true
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Log.d(TAG, "checkedExistedUsers: $email")
                _emailPattern.value = true
            }
            password?.isEmpty()!! -> {
                _passwordEmpty.value = true
            }
            else -> {
                repository.getLoginUsersDatabase(email ?: "", password ?: "", { response ->
                    Log.d(TAG, "loginUsers: email:$email, pass:$password")
                    Log.d(TAG, "loginUsers: response:$response")
                    _isLogin.value = response
                }, { error ->
                    Log.d(TAG, "loginUsers: $error, ${error.localizedMessage}")
                    _isError.value = error.localizedMessage
                })
            }
        }
    }
}