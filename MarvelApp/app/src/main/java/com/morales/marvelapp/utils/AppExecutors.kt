package com.morales.marvelapp.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import javax.inject.Singleton

@Singleton
class AppExecutors (diskIO: Executor,networkIO: Executor,mainThread: Executor) {

    companion object {
        class MainThreadExecutor : Executor {
            private val mainThreadHandler = Handler(Looper.getMainLooper())

            override fun execute(command: Runnable) {
                mainThreadHandler.post(command)
            }
        }

        const val THREAD_COUNT = 3
    }
}