package com.gojek.waetherdemo.di

import com.gojek.waetherdemo.ViewModelTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule::class])
interface TestNetworkComponent {

    fun inject(viewModel: ViewModelTest)

}