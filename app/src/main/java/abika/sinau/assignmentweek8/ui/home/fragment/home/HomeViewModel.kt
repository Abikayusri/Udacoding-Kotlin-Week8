package abika.sinau.assignmentweek8.ui.home.fragment.home

import abika.sinau.assignmentweek8.data.database.shops.ShopsEntity
import abika.sinau.assignmentweek8.repository.shops.RepositoryLocalShops
import abika.sinau.assignmentweek8.utils.extension.shortToast
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by Abika Chairul Yusri on 04/10/2020
 * Bismillahirrahmanirrahim
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val repository = RepositoryLocalShops(application.applicationContext)
    val context = getApplication<Application>().applicationContext

    private var _rAddShops = MutableLiveData<Boolean>()
    var rAddShops: LiveData<Boolean> = _rAddShops
    private var _rActionShops = MutableLiveData<Boolean>()
    var rActionShops: LiveData<Boolean> = _rActionShops
    private var _rShowShops = MutableLiveData<List<ShopsEntity>?>()
    var rShowShops: LiveData<List<ShopsEntity>?> = _rShowShops
    private var _isError = MutableLiveData<String>()
    var isError: LiveData<String> = _isError
    private var _isEmpty = MutableLiveData<String>()
    var isEmpty: LiveData<String> = _isEmpty
    private var _isSuccess = MutableLiveData<Boolean>()
    var isSuccess: LiveData<Boolean> = _isSuccess
    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    private val TAG = "HomeViewModel"

    fun showDataShops() {
        _isLoading.value = true
        repository.showItemShop({
            _isLoading.value = false
            _rShowShops.value = it
        }, {
            _isLoading.value = false
            _isError.value = it.localizedMessage
        })
    }

    fun addDataShops(shop: ShopsEntity) {
        _isLoading.value = true
        Log.d(TAG, "addDataShops: $shop")

        if (shop.title.toString().isEmpty() || shop.place.toString().isEmpty() ||
            shop.quantity.toString().isEmpty() || shop.note.toString().isEmpty()
        ) {
            _isSuccess.value = false
            shortToast(context, "Data tidak boleh ada yang kosong!")
        } else {
            repository.addItemShop(shop, {
                Log.d(TAG, "addDataShops: $it")
                _isSuccess.value = true
                _isLoading.value = false
                _rAddShops.value = it
            }, {
                Log.d(TAG, "addDataShops: $it, ${it.localizedMessage}")
                _isLoading.value = false
                _isError.value = it.localizedMessage
            })
        }
    }

    fun deleteDataShops(shop: ShopsEntity) {
        Log.d(TAG, "deleteDataShops: masuk sini 1 $shop")
        repository.deleteItemShop(shop, {
            Log.d(TAG, "deleteDataShops: masuk sini 1 $it")
            _rActionShops.value = it
            _isSuccess.value = true
        }, {
            Log.d(TAG, "deleteDataShops: masuk sini 2 $it, ${it.localizedMessage}")
            _isError.value = it.localizedMessage
        })
    }

    fun updateDataShops(shop: ShopsEntity) {
        if (shop.title.toString().isEmpty() || shop.place.toString().isEmpty() ||
            shop.quantity.toString().isEmpty() || shop.note.toString().isEmpty()
        ) {
            _isSuccess.value = false
            shortToast(context, "Data tidak boleh ada yang kosong!")
        } else {
            Log.d(TAG, "updateDataShops: masuk sini $shop")
            _isLoading.value = true
            repository.updateItemShop(shop, {
                Log.d(TAG, "updateDataShops: masuk sini 2 $it")
                _isLoading.value = false
                _isSuccess.value = true
                _rActionShops.value = it
//            _rShowShops.value =
            }, {
                Log.d(TAG, "updateDataShops: masuk sini 3 $it, ${it.localizedMessage}")
                _isLoading.value = false
                _isError.value = it.localizedMessage
            })
        }
    }
}