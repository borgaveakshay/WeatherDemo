package com.gojek.domain.utils

import io.reactivex.Observable

abstract class Mapper<in T, E> {

    abstract fun mapFrom(from: T): E


    fun observable(from: T): Observable<E> {

        return Observable.fromCallable { mapFrom(from) }
    }

    fun observable(from: List<T>): Observable<List<E>> {

        return Observable.fromCallable { from.map { mapFrom(it) } }
    }

}