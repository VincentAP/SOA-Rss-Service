package com.nakos.soa_uas_vincentardyanputra_2101658344.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo

open class BaseViewModel : ViewModel() {

    private var compositeDisposable: CompositeDisposable? = null

    fun Disposable.addToDisposable() {
        if (compositeDisposable == null) compositeDisposable = CompositeDisposable()
        compositeDisposable?.let { this.addTo(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
    }
}