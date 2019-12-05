package com.gojek.waetherdemo.views.viewmodels

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gojek.waetherdemo.di.component.DaggerDataComponent
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(), Observable {

    protected val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    private val registry: PropertyChangeRegistry = PropertyChangeRegistry()

    var isError: Boolean = false

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.remove(callback)

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registry.add(callback)

    }

    private fun notifyChange() {
        registry.notifyCallbacks(this, 0, null)
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }

}