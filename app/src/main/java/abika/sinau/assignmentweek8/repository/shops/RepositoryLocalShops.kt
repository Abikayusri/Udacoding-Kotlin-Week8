package abika.sinau.assignmentweek8.repository.shops

import abika.sinau.assignmentweek8.data.database.ShopsDatabase
import abika.sinau.assignmentweek8.data.database.shops.ShopsEntity
import android.content.Context
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Abika Chairul Yusri on 04/10/2020
 * Bismillahirrahmanirrahim
 */
class RepositoryLocalShops(context: Context) {
    private val TAG = "RepositoryLocalShops"
    private val shopDatabase = ShopsDatabase.getInstanceShops(context)

    fun addItemShop(
        shop: ShopsEntity,
        responseHandler: (Boolean) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { shopDatabase?.shopsDao()?.insertDataShops(shop) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Log.d(TAG, "addItemShop: ${it.localizedMessage}")
            }
            .subscribe({ response ->
//                responseHandler(true)
                responseHandler(true)
                Log.d(TAG, "addShops: $response")
            }, { error ->
                // error ketika gagal
                errorHandler(error)
                Log.d(TAG, "addItemShop: $error")
            })
    }

    fun showItemShop(
        responseHandler: (List<ShopsEntity>?) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { shopDatabase?.shopsDao()?.getAllDataShops() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun updateItemShop(
        shop: ShopsEntity,
        responseHandler: (Boolean?) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { shopDatabase?.shopsDao()?.updateDataShops(shop) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(true)
            }, {
                errorHandler(it)
            })
    }

    fun deleteItemShop(
        shop: ShopsEntity,
        responseHandler: (Boolean?) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable { shopDatabase?.shopsDao()?.deleteDataShops(shop) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(true)
            }, {
                errorHandler(it)
            })
    }
}