package com.gojek.waetherdemo.di.component

import com.gojek.waetherdemo.di.module.ContextModule
import com.gojek.waetherdemo.views.BaseActivity
import com.gojek.waetherdemo.views.viewmodels.BaseViewModel
import com.gojek.waetherdemo.di.module.NetworkModule
import com.gojek.waetherdemo.di.module.DbModule
import com.gojek.waetherdemo.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DbModule::class, ContextModule::class])
interface DataComponent {
    fun inject(activity: BaseActivity)
    //    fun inject(viewModel: BaseViewModel)
    fun inject(mainActivity: MainActivity)
}