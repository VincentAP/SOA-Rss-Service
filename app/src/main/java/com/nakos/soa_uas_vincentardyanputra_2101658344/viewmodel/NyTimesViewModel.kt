package com.nakos.soa_uas_vincentardyanputra_2101658344.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nakos.soa_uas_vincentardyanputra_2101658344.Resource
import com.nakos.soa_uas_vincentardyanputra_2101658344.data.NyTimesService
import com.nakos.soa_uas_vincentardyanputra_2101658344.model.NyTimesItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NyTimesViewModel @Inject constructor(
    private val nyTimesService: NyTimesService
) : BaseViewModel() {

    private val _nyTimesItem: MutableLiveData<Resource<List<NyTimesItemList>>> = MutableLiveData()
    val nyTimesItem: LiveData<Resource<List<NyTimesItemList>>> = _nyTimesItem

    fun getNyTimesItem() {
        _nyTimesItem.value = Resource.loading(null)
        nyTimesService.getNyTimesItem()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.channel?.item?.isNotEmpty() == true)
                    _nyTimesItem.value = Resource.success(it.channel?.item)
                else _nyTimesItem.value = Resource.empty(null)
            }, {
                _nyTimesItem.value = Resource.error(it.localizedMessage ?: "unknown error", null)
                it.printStackTrace()
            }).addToDisposable()
    }
}