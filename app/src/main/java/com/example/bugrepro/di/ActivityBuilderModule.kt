package com.example.bugrepro.di

import com.example.bugrepro.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

//    @MainScope
    @ContributesAndroidInjector(
        modules = [PagerViewModelModule::class, MainFragmentBuildersModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}