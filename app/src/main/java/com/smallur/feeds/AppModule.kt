package com.smallur.feeds

import android.app.Application
import android.content.Context
import com.smallur.feeds.data.DataModule
import com.smallur.feeds.presentation.base.SchedulerFacade
import com.smallur.feeds.presentation.base.SchedulersFacadeImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Sanjay
 * Class to provide [Application] context and [SchedulerFacade] instance
 * */
@Module(includes = [DataModule::class])
class AppModule {

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSchedulersFacade(schedulersFacadeImpl: SchedulersFacadeImpl): SchedulerFacade = schedulersFacadeImpl
}