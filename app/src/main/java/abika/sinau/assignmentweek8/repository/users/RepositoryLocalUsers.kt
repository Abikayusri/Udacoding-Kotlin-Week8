package abika.sinau.assignmentweek8.repository.users

import abika.sinau.assignmentweek8.data.database.ShopsDatabase
import abika.sinau.assignmentweek8.data.database.user.UsersEntity
import android.content.Context
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
class RepositoryLocalUsers(context: Context) {
    private val TAG = "RepositoryLocalUsers"
    private val userDatabase = ShopsDatabase.getInstanceShops(context)

    fun addUsers(
        user: UsersEntity,
        responseHandler: (Boolean) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { userDatabase?.usersDao()?.insertDataUsers(user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                responseHandler(true)
                Log.d(TAG, "addUsers 1: $response")
            }, { error ->
                // error ketika gagal
                Log.d(TAG, "addUsers 2: $error")
                errorHandler(error)
            })
    }

    fun getLoginUsersDatabase(
        email: String,
        password: String,
        responseHandler: (Int?) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { userDatabase?.usersDao()?.getSelectedDataUsers(email, password) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "getLoginUsersDatabase 1: email: $email, pass:$password, $it")
                responseHandler(it)
            }, {
                Log.d(TAG, "getLoginUsersDatabase 2: email: $email, pass:$password, $it")
                errorHandler(it)
            })
    }

    fun getCheckExistedEmailDatabase(
        name: String,
        email: String,
        responseHandler: (Int?) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { userDatabase?.usersDao()?.getSelectedUsers(name, email) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "getCheckExistedEmailDatabase 1: name: $name, email:$email, $it")
                responseHandler(it)
            }, {
                Log.d(TAG, "getCheckExistedEmailDatabase 2: name: $name, email:$email, $it")
                errorHandler(it)
            })
    }
}