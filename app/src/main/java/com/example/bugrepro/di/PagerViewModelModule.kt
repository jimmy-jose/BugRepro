package com.example.bugrepro.di

import androidx.lifecycle.ViewModel
import com.example.bugrepro.PagerItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PagerViewModelModule {
//    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(PagerItemViewModel::class)
    abstract fun bindPagerViewModel(viewModel: PagerItemViewModel): ViewModel

}