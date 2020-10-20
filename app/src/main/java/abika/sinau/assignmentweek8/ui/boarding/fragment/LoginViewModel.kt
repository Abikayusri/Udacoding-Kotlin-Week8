package abika.sinau.assignmentweek8.ui.boarding.fragment

import abika.sinau.assignmentweek8.repository.users.RepositoryLocalUsers
import android.app.Application
import android.util.Log
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

    private val TAG = "LoginViewModel"

    fun loginUsers(email: String?, password: String?) {
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