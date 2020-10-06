package abika.sinau.assignmentweek8.ui.register

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
class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    val repository = RepositoryLocalUsers(application.applicationContext)

    var _rActionUser = MutableLiveData<Boolean>()
    var rActionMhs: LiveData<Boolean> = _rActionUser
    var _isError = MutableLiveData<String>()
    var isError: LiveData<String> = _isError
    var _isEmpty = MutableLiveData<String>()
    var isEmpty: LiveData<String> = _isEmpty
    var _isSuccess = MutableLiveData<Boolean>()
    var isSuccess: LiveData<Boolean> = _isSuccess
    var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    fun insertUsers(user: UsersEntity) {
        _isLoading.value = true
        repository.addUsers(user,
            {
                _isLoading.value = false
                _isSuccess.value = it
            }, {
                _isLoading.value = false
                _isError.value = it.localizedMessage
            })
    }
}