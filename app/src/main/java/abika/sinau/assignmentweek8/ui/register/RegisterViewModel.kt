package abika.sinau.assignmentweek8.ui.register

import abika.sinau.assignmentweek8.data.database.user.UsersEntity
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
class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    val repository = RepositoryLocalUsers(application.applicationContext)
    val context = getApplication<Application>().applicationContext

    var _rActionUser = MutableLiveData<Boolean>()
    var rActionMhs: LiveData<Boolean> = _rActionUser
    var _isError = MutableLiveData<Throwable>()
    var isError: LiveData<Throwable> = _isError
    var _isSuccess = MutableLiveData<Boolean>()
    var isSuccess: LiveData<Boolean> = _isSuccess
    var _isLogin = MutableLiveData<Int>()
    var isLogin: LiveData<Int> = _isLogin
    var _isEmpty = MutableLiveData<Int>()
    var isEmpty: LiveData<Int> = _isEmpty
    var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    // state error
    var _emailEmpty = MutableLiveData<Boolean>()
    var emailEmpty: LiveData<Boolean> = _emailEmpty
    var _emailPattern = MutableLiveData<Boolean>()
    var emailPattern: LiveData<Boolean> = _emailPattern
    var _nameEmpty = MutableLiveData<Boolean>()
    var nameEmpty: LiveData<Boolean> = _nameEmpty
    var _passwordEmpty = MutableLiveData<Boolean>()
    var passwordEmpty: LiveData<Boolean> = _passwordEmpty
    var _confPasswordEmpty = MutableLiveData<Boolean>()
    var confPasswordEmpty: LiveData<Boolean> = _passwordEmpty
    var _passwordNotSame = MutableLiveData<Boolean>()
    var passwordNotSame: LiveData<Boolean> = _passwordNotSame

    private val TAG = "RegisterViewModel"

    fun insertUsers(password: String, conPassword: String, user: UsersEntity) {
        when {
            password.isEmpty() -> {
                _passwordEmpty.value = true
            }
            conPassword.isEmpty() -> {
                _confPasswordEmpty.value = true
            }
            password != conPassword -> {
                _passwordNotSame.value = true
            }
            else -> {
                _isLoading.value = true
                Log.d(TAG, "insertUsers 1: $user")
                repository.addUsers(user,
                    {
                        Log.d(TAG, "insertUsers 2: $it")
                        _isLoading.value = false
                        _isSuccess.value = true
                    }, {
                        Log.d(TAG, "insertUsers 3: $it")
                        _isLoading.value = false
                        _isSuccess.value = false
                        _isError.value = it
                    })
            }
        }
    }

    fun checkedExistedUsers(name: String, email: String) {

        when {
            name.isEmpty() -> {
                _nameEmpty.value = true
            }
            email.isEmpty() -> {
                _emailEmpty.value = true
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Log.d(TAG, "checkedExistedUsers: $")
                _emailPattern.value = true
            }
            else -> {
                repository.getCheckExistedEmailDatabase(name ?: "", email ?: "", { response ->
                    Log.d(TAG, "checkedExistedUsers 1: name:$name, email:$email, $response")
                    _isEmpty.value = response
                }, { error ->
                    Log.d(TAG, "checkedExistedUsers 2: name:$name, email:$email, $error")
                    Log.d(TAG, "loginUsers: $error, ${error.localizedMessage}")
                    _isSuccess.value = false
                    _isError.value = error
                })
            }
        }
    }
}