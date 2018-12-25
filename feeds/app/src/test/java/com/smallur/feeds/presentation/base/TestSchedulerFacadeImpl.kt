package com.smallur.feeds.presentation.base

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import javax.inject.Inject

class TestSchedulerFacadeImpl @Inject constructor(private val testScheduler: TestScheduler) : SchedulerFacade {

    /**
     * IO thread pool scheduler
     */
    override fun io(): Scheduler {
        return this.testScheduler
    }

    /**
     * Computation thread pool scheduler
     */
    override fun computation(): Scheduler {
        return testScheduler
    }

    /**
     * Main Thread scheduler
     */
    override fun ui(): Scheduler {
        return testScheduler
    }
}