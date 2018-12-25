package com.smallur.feeds

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Sanjay
 * Component builder class
 * */
@Singleton
@Component(modules = [(AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: ApplicationConfig)
}