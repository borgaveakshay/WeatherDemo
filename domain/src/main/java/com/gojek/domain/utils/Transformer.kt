package com.gojek.domain.utils

import io.reactivex.ObservableTransformer

abstract class Transformer<T> : ObservableTransformer<T, T>