package abika.sinau.assignmentweek8.repository.users

import abika.sinau.assignmentweek8.data.database.user.UsersDao
import abika.sinau.assignmentweek8.data.database.user.UsersDatabase
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
class RepositoryLocalUsersImpl(private val context: Context, private val dao: UsersDao) :
    RepositoryLocalUser {
    private val TAG = "RepositoryLocalUsersImpl"
//    private val userDatabase = UsersDatabase.getInstanceUsers(context)

    override fun addUsers(
        user: UsersEntity,
        responseHandler: (Boolean?) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { dao.insertDataUsers(user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                responseHandler(true)
                Log.d(TAG, "addUsers: $response")
            }, { error ->
                // error ketika gagal
                errorHandler(error)
            })
    }

    override fun getLoginUsersDatabase(
        email: String,
        password: String,
        responseHandler: (Boolean?) -> Unit, errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { dao.getSelectedDataUsers(email, password) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(true)
            }, {
                errorHandler(it)
            })
    }

//    fun addUsers(
//        user: UsersEntity,
//        responseHandler: (Boolean) -> Unit,
//        errorHandler: (Throwable) -> Unit
//    ) {
//        Observable.fromCallable { userDatabase?.usersDao()?.insertDataUsers(user) }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ response ->
//                responseHandler(true)
//                Log.d(TAG, "addUsers: $response")
//            }, { error ->
//                // error ketika gagal
//                errorHandler(error)
//            })
//    }
//
//    fun getLoginUsersDatabase(
//        email: String,
//        password: String,
//        responseHandler: (Boolean?) -> Unit,
//        errorHandler: (Throwable) -> Unit
//    ) {
//        Observable.fromCallable { userDatabase?.usersDao()?.getSelectedDataUsers(email, password) }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                responseHandler(true)
//            }, {
//                errorHandler(it)
//            })
//    }
}