package com.nakos.soa_uas_vincentardyanputra_2101658344.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nakos.soa_uas_vincentardyanputra_2101658344.Resource
import com.nakos.soa_uas_vincentardyanputra_2101658344.data.News24Service
import com.nakos.soa_uas_vincentardyanputra_2101658344.model.News24ItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class News24ViewModel @Inject constructor(
    private val news24Service: News24Service
) : BaseViewModel() {

    private val _news24Item: MutableLiveData<Resource<List<News24ItemList>>> = MutableLiveData()
    val news24Item: LiveData<Resource<List<News24ItemList>>> = _news24Item

    fun getNews24Item() {
        _news24Item.value = Resource.loading(null)
        news24Service.getNews24Item()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.channel?.item?.isNotEmpty() == true)
                    _news24Item.value = Resource.success(it.channel?.item)
                else _news24Item.value = Resource.empty(null)
            }, {
                _news24Item.value = Resource.error(it.localizedMessage ?: "unknown error", null)
                it.printStackTrace()
            }).addToDisposable()
    }
}