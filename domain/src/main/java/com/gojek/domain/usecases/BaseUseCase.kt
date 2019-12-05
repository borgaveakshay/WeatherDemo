package com.gojek.domain.usecases

import com.gojek.domain.utils.Transformer
import io.reactivex.Observable

abstract class BaseUseCase<REQ, RES>(private val transformer: Transformer<RES>) {

    abstract fun createObservable(request: REQ? = null): Observable<RES>

    fun observable(request: REQ? = null): Observable<RES> {

        return createObservable(request).compose(transformer)
    }
}