package com.example.bugrepro.di

import com.example.bugrepro.PagerItemFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePagerItemFragment(): PagerItemFragment

}