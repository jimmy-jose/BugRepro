package com.example.bugrepro.di

import android.app.Application
import com.example.bugrepro.BugReproApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, AppModule::class]
)
interface AppComponent : AndroidInjector<BugReproApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}