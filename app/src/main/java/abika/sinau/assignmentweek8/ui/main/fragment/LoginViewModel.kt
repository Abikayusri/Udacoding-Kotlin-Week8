package abika.sinau.assignmentweek8.ui.main.fragment

import abika.sinau.assignmentweek8.data.database.user.UsersEntity
import abika.sinau.assignmentweek8.repository.users.RepositoryLocalUsers
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val repository = RepositoryLocalUsers(application.applicationContext)

    val rShowUsers = MutableLiveData<List<UsersEntity>>()
    var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading
    var _isError = MutableLiveData<String>()
    var isError: LiveData<String> = _isError

    fun loginUsers(email: String?, password: String?) {
        _isLoading.value = true
        repository.getLoginUsersDatabase(email ?: "", password ?: "", { response ->

            _isLoading.value = false
        }, { error ->
            _isLoading.value = false
            _isError.value = error.localizedMessage
        })
    }
}