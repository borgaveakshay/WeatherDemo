package com.gojek.waetherdemo.utils

import com.gojek.domain.utils.Transformer
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AsyncTransformer<T> : Transformer<T>() {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
}