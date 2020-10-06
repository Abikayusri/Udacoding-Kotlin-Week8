package abika.sinau.assignmentweek8.ui.home.fragment.home

import abika.sinau.assignmentweek8.data.database.shops.ShopsEntity
import abika.sinau.assignmentweek8.repository.shops.RepositoryLocalShops
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by Abika Chairul Yusri on 04/10/2020
 * Bismillahirrahmanirrahim
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val repository = RepositoryLocalShops(application.applicationContext)

    private var _rAddShops = MutableLiveData<Boolean>()
    var rAddShops: LiveData<Boolean> = _rAddShops
    private var _rUpdateShops = MutableLiveData<Boolean>()
    var rUpdateShops: LiveData<Boolean> = _rUpdateShops
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
        repository.addItemShop(shop, {
            _isLoading.value = false
            _rAddShops.value = it
        }, {
            _isLoading.value = false
            _isError.value = it.localizedMessage
        })
    }

    fun deleteDataShops() {

    }

    fun updateDataShops(shop: ShopsEntity) {
        _isLoading.value = true
        repository.updateItemShop(shop, {
            _isLoading.value = false
            _rUpdateShops.value = it
        }, {
            _isLoading.value = false
            _isError.value = it.localizedMessage
        })
    }
}